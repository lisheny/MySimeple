package com.lisheny.mytab.tools;

import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;

/*
 */
public class ByteTool {

	//判断数据是否是鉴权命令
	public static boolean isAuthCmd(byte[] src){

		if(src.length < 10) return false;
		byte[] authCmdExample = {(byte)0x88,};
		//byte[] authCmdExample = {(byte)0x84,(byte)0xDA,0x00,0x00,0x22};
//前9字节为imsi
		try {
			return compareByteArray(ByteTool.subBytes(src,10,1),authCmdExample,1);
		}catch (Exception e){
			return false;
		}
	}

	public static boolean isAuthCmd80(byte[] src){
		byte[] authCmdExample = {(byte)0x88,(byte)0x00,0x00,(byte)0x80};

		return compareByteArray(ByteTool.subBytes(src,1,4),authCmdExample,4);
	}

	public static boolean isAuthCmd81(byte[] src){
		byte[] authCmdExample = {(byte)0x88,0x00,0x00,(byte)0x81};

		return compareByteArray(ByteTool.subBytes(src,1,4),authCmdExample,4);
	}
	
	/*
	 * 功能描述：比较source与des，如果二者长度不等，则在source后补若干个spacing_byte使其长度相等
	 */
	public static byte[] padding(byte[] source,byte[] des,byte spacing_byte){
		int s_len = source.length;
		int d_len = des.length;
		if(s_len==d_len) return des;
		if(s_len<d_len) return null;

		for(int i=0;i<d_len;i++){
			source[i]=des[i];
		}
		for(int i=d_len;i<s_len;i++){
			source[i]=spacing_byte;
		}
		return source;
	}
	
	/*
	 * Description:将ascii数组转换为int型，最长支持6位数字
	 */
	public static int asciiArrayToInt(byte[] ascii){
		int temp=0;
		for(int i=0;i<ascii.length;i++){
			temp+=ascii[i]&0x0f;
		}
		return temp;
	}
	
	/*
	 * Description:比较两个数组中的内容是否相同
	 */
	public static boolean compareByteArray(byte[] src1,byte[]src2,int len){
		if(len==0){
			return true;
		}
		for(int i=0;i<len;i++){
			if(src1[i]!=src2[i]){
				return false;
			}
		}
		return true;
	}
	/*
	 * Description:将int转为相应的byte[]形式，大端模式
	 */
	public static byte[] intToByteArray(int num){
		byte[] temp=new byte[2];
		temp[0]=(byte)((num>>8)&0xFF);
		temp[1]=(byte)((num>>0)&0xFF);
		return temp;
	}
	/*
	 * Description:将long转为相应的byte[]形式，大端模式
	 */
	public static byte[] longToByteArray(long num){
		byte[] temp=new byte[4];
		temp[0]=(byte)((num>>56)&0xFF);
		temp[1]=(byte)((num>>48)&0xFF);
		temp[2]=(byte)((num>>40)&0xFF);
		temp[3]=(byte)((num>>32)&0xFF);
		temp[4]=(byte)((num>>24)&0xFF);
		temp[5]=(byte)((num>>16)&0xFF);
		temp[6]=(byte)((num>>8)&0xFF);
		temp[7]=(byte)((num>>0)&0xFF);
		return temp;
	}
	/*
	 * @Description：src2追加到src1之后返回新的byte[]
	 */
	public static byte[] appendArray(byte[] src1,byte[] src2){
		if((src1==null)&&(src2==null)){
			return null;
		}
		if(src1==null){
			return src2;
		}
		if(src2==null){
			return src1;
		}
		byte[] temp=new byte[src1.length+src2.length];
		int i=0;
		for(;i<src1.length;i++){
			temp[i]=src1[i];
		}
		for(int j=0;j<src2.length;j++){
			temp[i+j]=src2[j];
		}
		return temp;
	}
	
	/*
	 * @Description:对给定的byte数组进行切片操作（切片复制）
	 * src:源数据
	 * start:切片起始位置，最小为0
	 * end:切片结束位置，end的前一项为切片的最后一个元素。
	 * return：切片失败返回null
	 * 			切片成功返回获取的byte[]
	 */
	public static byte[] subByteArray(byte[] src,int start,int end){
		if(src==null){
			return null;
		}
		int len=src.length;
		if(len==0){
			return null;
		}
		if(start<0){
			return null;
		}
		if(end<=0){
			return null;
		}
		if(start>=end){
			return null;
		}
		byte[] out=new byte[end-start];
		for(int i=start,j=0;i<end;i++,j++){
			out[j]=src[i];
		}
		return out;
	}
	
	/*
     * 功能说明：从一个byte数组中截取一个子数组。
     */
    public static byte[] subBytes(byte[] src, int start, int len)
    {
        if ((src == null) || (src.length < (start + len)))
        {
            return null;
        }
        byte[] subData = new byte[len];
        for (int i = 0; i < len; i++)
        {
            subData[i] = src[i + start];
        }
        return subData;
    }
    
	/*
	 * @Description:将给定的byte数组转换为HEX字符串形式
	 * @Ep:{0x05,0x98,0xf9}-->"0598f9"
	 */
	public static String BytesToHexString(byte []data, int len){
		String str="";
		if((data.length==0)||(data.length<len)){
			return str;
		}
		for(int k=0;k<len;k++){
			byte h=(byte) ((data[k]>>4)&0x0F);
			byte l=(byte) (data[k]&0x0F);
			str+= Integer.toHexString(h)+ Integer.toHexString(l);
		}
		return str.toUpperCase();
	}

	/*
	 * @Description:将给定的Hex字符串转换为16进制byte数组，eg."125f4a0e"-->0x12,0x5f,0x4e,0x0e
	 */
	public static byte[] HexStringToBytes(String HexStr) throws UnsupportedEncodingException {
		byte[] str = HexStr.getBytes("US-ASCII");
		//长度检查
		if((str.length%2)!=0){
			throw new UnsupportedEncodingException("Hex字符串长度不符合要求");
		}
		byte[] temp=new byte[str.length/2];
		byte h;
		for(int i=0;i<str.length;i++){
			//a-f 0x41-0x46		A-F 0x61-0x66	0-9 0x30-0x39
			if(((str[i]&0xf0)==0x60)||((str[i]&0xf0)==0x40)){
				if(((str[i]&0x0f)>0x06)||((str[i]&0x0f)==0x00)){
					throw new UnsupportedEncodingException("Hex字符串长中包含非法字符");
				}else{
					h=(byte)((str[i]&0x07)+9);
				}
			}else if((str[i]&0xf0)==0x30){
				if((str[i]&0x0f)>0x09){
					throw new UnsupportedEncodingException("Hex字符串长中包含非法字符");
				}else{
					h=(byte)(str[i]&0x0f);
				}
			}else{
				throw new UnsupportedEncodingException("Hex字符串长中包含非法字符");
			}
			
			if((i%2)==0){
				temp[i/2] = (byte) ((h&0x0f)<<4);
			}else{//奇数项为后半字节
				temp[i/2] |= (byte) (h&0x0f);
			}
		}
		return temp;
	}
	
	/*
	 * @Description:将ASCII编码的byte数组转换为String
	 * 		exp:0x31,0x32,0x33 -> "123"
	 */
	public static String AsciiBytesToString(byte[] asciiBytes){
		StringBuffer tStringBuf = new StringBuffer();
		char[] tChars=new char[asciiBytes.length];
		for(int i=0; i<asciiBytes.length; i++){
			tChars[i]=(char)asciiBytes[i];
		}
		tStringBuf.append(tChars);      
		return tStringBuf.toString();
	}
	
	/*
	 * @Description:将ASCII编码的byte数组转换为String
	 * 		exp:"123" -> 0x31,0x32,0x33 
	 */
	public static byte[] StringToAsciiBytes(String asciiStr) throws UnsupportedEncodingException {
		return asciiStr.getBytes("US-ASCII");
	}
	
	/*
	 * @Description:将UTF-8格式的数据转换为UCS2格式
	 * 				暂时只支持最长3字节的UTF-8编码
	 */
	public static byte[] utf8ToUcs2(byte[] data) throws DataFormatException {
    	byte[] cache=new byte[2*data.length];//将buffer的大小设置为原始数据的2倍，确保不溢出
    	int cacheIndex=0;
    	byte temp;
    	
    	for(int i=0;i<data.length;){
    		if((data[i]&0x80)==0x00){
    			//0000 0000-0000 007F -- 0xxxxxxx
    			temp=data[i];
    			cache[cacheIndex]=0x00;
    			cache[cacheIndex+1]=temp;
    			cacheIndex+=2;
    			i+=1;
    		}else if((data[i]&0xE0)==0xC0){
    			//0000 0080-0000 07FF -- 110xxxxx 10xxxxxx
    			temp=(byte) ((data[i]&0x1f)>>2);
    			cache[cacheIndex]=temp;
    			temp=(byte) (((data[i]&0x03)<<6)|(data[i+1]&0x3f));
    			cache[cacheIndex+1]=temp;
    			cacheIndex+=2;
    			i+=2;
    		}else if((data[i]&0xF0)==0xE0){
    			//0000 0800-0000 FFFF -- 1110xxxx 10xxxxxx 10xxxxxx
    			temp=(byte) (((data[i]&0x0f)<<4)|((data[i+1]&0x3f)>>2));
    			cache[cacheIndex]=temp;
    			temp=(byte) (((data[i+1]&0x03)<<6)|(data[i+2]&0x3f));
    			cache[cacheIndex+1]=temp;
    			cacheIndex+=2;
    			i+=3;
    		}else{
    			throw new DataFormatException("utf-8数据格式错误！");
    		}
    	}
    	byte[] result=new byte[cacheIndex];
    	for(int i=0;i<cacheIndex;i++){
    		result[i]=cache[i];
    	}
    	return result;
    }
	
	/*
     * @功能描述：将UTF-8编码格式转换为UCS2格式
     * 			暂时只支持最长3字节的UTF-8编码
     */
    public static byte[] Ucs2Toutf8(byte[] data){
    	byte[] cache=new byte[2*data.length];
    	int cacheIndex=0;
    	byte temp;

		for(int i=0;i<data.length;i+=2){
    		if((data[i]==0x00)&&((data[i+1]&0x80)==0x00)){
    			//0000 0000-0000 007F -- 0xxxxxxx
    			temp=data[i+1];
    			cache[cacheIndex]=temp;
    			cacheIndex+=1;
    		}else if((data[i]&0xF8)==0x00){
    			//0000 0080-0000 07 FF -- 110xxxxx 10xxxxxx
    			temp=(byte) (((data[i]&0x07)<<2)|((data[i+1]>>6)&0x03)|0xC0);
    			cache[cacheIndex]=temp;
    			temp=(byte) ((data[i+1]&0x3F)|0x80);
    			cache[cacheIndex+1]=temp;
    			cacheIndex+=2;
    		}else{
    			//0000 0800-0000 FFFF -- 1110xxxx 10xxxxxx 10xxxxxx
    			temp=(byte) (((data[i]&0xf0)>>4)|0xE0);
    			cache[cacheIndex]=temp;
    			temp=(byte) (((data[i]&0x0f)<<2)|((data[i+1]&0xc0)>>6)|0x80);
    			cache[cacheIndex+1]=temp;
    			temp=(byte) ((data[i+1]&0x3f)|0x80);
    			cache[cacheIndex+2]=temp;
    			cacheIndex+=3;
    		}
    	}
    	byte[] result=new byte[cacheIndex];
    	for(int i=0;i<cacheIndex;i++){
    		result[i]=cache[i];
    	}
    	return result;
    }
    
    /**
     * @description：将卡端的响应的日期数据转换成YYYY-MM-DD格式
     * @param date
     * @return
     */
    public static String formatDate(byte[] date){
    	if(date.length!=4){
    		return null;
    	}
    	StringBuffer sb = new StringBuffer();
    	sb.append(ByteTool.BytesToHexString(new byte[]{date[0],date[1]}, 2));
    	sb.append("-");
    	sb.append(ByteTool.BytesToHexString(new byte[]{date[2]}, 1));
    	sb.append("-");
    	sb.append(ByteTool.BytesToHexString(new byte[]{date[3]}, 1));
    	return sb.toString();
    }
    /**
     * @description：将卡端的响应的日期数据转换成HH:mm:ss格式
     * @param time
     * @return
     */
    public static String formatTime(byte[] time){
    	if(time.length!=3){
    		return null;
    	}
    	StringBuffer sb = new StringBuffer();
    	sb.append(ByteTool.BytesToHexString(new byte[]{time[0]}, 1));
    	sb.append(":");
    	sb.append(ByteTool.BytesToHexString(new byte[]{time[1]}, 1));
    	sb.append(":");
    	sb.append(ByteTool.BytesToHexString(new byte[]{time[2]}, 1));
    	return sb.toString();
    }
    /**
     * @description：将16进制的byte型数组的转换为整数，如{0x12,0x34,0x56,0x78}，即0x12345678-->305419896
     * @param data
     * @return
     */
    public static int byteArrayToInteger(byte[] data){
		int len = data.length;
		int result = 0;
		for(int i=0;i<len;i++){
			int temp = ((len-1-i)*8);
			result += data[i]<<temp&(0xFF<<temp);
		}
		return result;
	}
    
	/**
	 * @description：将两个长度相等的字节数组进行异或运算
	 * @param data1
	 * @param data2
	 * @return 运算结果，如果data1和data2长度不相同，则返回null
	 */
	public static byte[] exclusive_OR(byte[] data1,byte[] data2){
		if(data1.length!=data2.length){
			return null;
		}
		byte[] temp = new byte[data1.length];
		for(int i=0;i<data1.length;i++){
			temp[i] = (byte)(data1[i]^data2[i]);
		}
		return temp;
	}
	/**
	 * @description：比较两个"yyyy-MM-dd"日期类型
	 * @param data1 格式必须为“yyyy-MM-dd”
	 * @param data2
	 * @return 如果data1比data2早，则返回1，如果晚则返回-1，如果相等则返回0；
	 */
	public static int compareDate(String data1, String data2){
		int d1 = Integer.parseInt(data1.replace("-", ""));
		int d2 = Integer.parseInt(data2.replace("-", ""));
		if(d1==d2){
			return 0;
		}
		if(d1<d2){
			return 1;
		}
		if(d1>d2){
			return -1;
		}
		return 0;
	}
	
	/**
	 * @description：yyyy-MM-dd HH:mm:ss 转换成byte[]
	 * @param date
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] formatStringDate(String date) throws UnsupportedEncodingException {
		String yy = date.substring(0, 4);
		String mm = date.substring(5, 7);
		String dd = date.substring(8,10);
		String hh = date.substring(11,13);
		String mm1 = date.substring(14,16);
		String ss = date.substring(17,19);
		String temp = yy+mm+dd+hh+mm1+ss;
		return ByteTool.HexStringToBytes(temp);
	}
	
	/**
	 * @description: memcpy
	 * 
	 */
	public static void ByteArrayMemcpy(byte[] dest, int index, byte[] src){
		for(int i=0; i<src.length && (i+index)<dest.length; i++){
			dest[i+index] = src[i];
		}
	}
	
	/**
	 * @description: memcpy
	 * 
	 */
	public static void ByteArrayMemcpy(byte[] dest, int index, byte[] src, int len){
		if(len > src.length){
			len = src.length;
		}
		for(int i=0; i<len && (i+index)<dest.length; i++){
			dest[i+index] = src[i];
		}
	}

	public static String getImsi(byte[] IMSIbytes)
	{
		byte[] resImsi = subBytes(IMSIbytes, 1, 8);
		char[] tempImsi = BytesToHexString(resImsi,8).toCharArray();
		char temp;
		for (int i = 0; i < tempImsi.length; i += 2)
		{
			temp = tempImsi[i];
			tempImsi[i] = tempImsi[i + 1];
			tempImsi[i + 1] = temp;
		}

		return  new String(tempImsi,1,15);

	}

}
