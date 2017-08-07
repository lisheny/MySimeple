package com.lisheny.mytab.http;

import com.blankj.utilcode.utils.ToastUtils;
import com.lisheny.mytab.mvp.BaseApplication;
import com.lisheny.mytab.tools.Constant;
import com.lisheny.mytab.tools.MD5Utils;
import com.orhanobut.logger.Logger;

import org.json.JSONException;

import java.io.File;

import okhttp3.Call;
import okhttp3.Request;

/**
 * <pre>
 *     author : lisheny
 *     e-mail : 1020044519@qq.com
 *     time   : 2017/07/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class DoNetWork extends RequestPackageImpl {
    private static final String TAG = DoNetWork.class.getSimpleName();

    public DoNetWork(String command) {
        super(command);
    }

    /**
     * File sdcard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
       /storage/emulated/0/Download/Application.apk
       String apkPath = sdcard + "/" + AppUpdateBiz.APK_NAME;
     * 文件下载
     * @param downloadUrl
     * @param destFileDir
     * @param filename
     * @param apkPath
     */
    public void downloadFile(final String downloadUrl, String destFileDir, String filename, final String apkPath) {

        // 下载文件
        OkHttpClientManager.okHttpGetDownloadFile(downloadUrl, TAG, destFileDir, filename,
                new OkHttpFileResponseHandler() {
                    @Override
                    public void onBefore(Request request, int id) {
                        Logger.d(TAG, "onBefore() --> " + request.url().toString());
                    }

                    @Override
                    public void onFileSucceed(File response, int id) {
                        Logger.d(TAG, "onFileSucceed() --> response：" + response + "  id：" + id);
                    }

                    @Override
                    public void onUpdateProgress(float progress, long total, int id) {
                        Logger.d(TAG, "onUpdateProgress() --> progress：" + progress + "  total：" + total + "  id：" + id);
                    }

                    @Override
                    public void onFailure(Call call, Exception e, int id) {

                        Logger.d(TAG, "onUpdateProgress() --> call：" + call.request().url() +
                                "  e：" + e.getMessage() + "  id：" + id);
                    }
                });
    }

    /**
     * 添加或删除我的滤镜
     */
    public void addOrDelFilter(String mFilterno, int mOptype, MyOkHttpResponseHandler myOkHttpResponseHandler) {
        if (BaseApplication.getUseBeen() == null) {
            ToastUtils.showShortToast("请先登录");
            return;
        }

        try {
            requestPackage.put(uid, BaseApplication.getUseBeen().getId());
            requestPackage.put(filterno, mFilterno);
            requestPackage.put(optype, String.valueOf(mOptype));
            sendPostRequest(URL.HOST_URL, null, requestPackage, myOkHttpResponseHandler);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //重置密码
    public void resetPwd(String phone, String mCode, String mNewPwd,
                         MyOkHttpResponseHandler myOkHttpResponseHandler) {
        try {
            requestPackage.put(username, phone);
            requestPackage.put(authcode, mCode);
            requestPackage.put(newpwd, MD5Utils.getMd5(mNewPwd));
            sendPostRequest(URL.HOST_URL, null, requestPackage, myOkHttpResponseHandler);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getUrl(MyOkHttpResponseHandler myOkHttpResponseHandler) {
        sendPostRequest(URL.HOST_URL, null, requestPackage, myOkHttpResponseHandler);
    }

    public void bandleBleHeadset(String mDevid, MyOkHttpResponseHandler myOkHttpResponseHandler) {
        if (BaseApplication.getUseBeen() == null) {
            ToastUtils.showShortToast("请先登录");
            return;
        }

        try {
            requestPackage.put(uid, BaseApplication.getUseBeen().getId());
            requestPackage.put(devid, mDevid);
            sendPostRequest(URL.HOST_URL, null, requestPackage, myOkHttpResponseHandler);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void changePwd(String mOldPwd, String mNewPwd, MyOkHttpResponseHandler myOkHttpResponseHandler) {
        if (BaseApplication.getUseBeen() == null) {
            ToastUtils.showShortToast("请先登录");
            return;
        }

        try {
            requestPackage.put(username, BaseApplication.getUseBeen().getUsername());
            requestPackage.put(password, MD5Utils.getMd5(mOldPwd));
            requestPackage.put(newpwd, MD5Utils.getMd5(mNewPwd));
            sendPostRequest(URL.HOST_URL, null, requestPackage, myOkHttpResponseHandler);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void changeUsenameOrNumber(String mUsename, String mNumber, int changeType,
                                      MyOkHttpResponseHandler myOkHttpResponseHandler) {
        if (BaseApplication.getUseBeen() == null) {
            ToastUtils.showShortToast("请先登录");
            return;
        }

        try {
            requestPackage.put(uid, BaseApplication.getUseBeen().getId());
            requestPackage.put(edittype, String.valueOf(changeType));
            requestPackage.put(username, mUsename);
            requestPackage.put(mobile, mNumber);
            sendPostRequest(URL.HOST_URL, null, requestPackage, myOkHttpResponseHandler);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 只要求 UID 参数的接口，传不同 command 请求不同接口
     *
     * @param myOkHttpResponseHandler
     */
    public void getNet(MyOkHttpResponseHandler myOkHttpResponseHandler) {
        if (BaseApplication.getUseBeen() == null) {
            ToastUtils.showShortToast("请先登录");
            return;
        }
        try {
            requestPackage.put(uid, BaseApplication.getUseBeen().getId());
            sendPostRequest(URL.HOST_URL, null, requestPackage, myOkHttpResponseHandler);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取拓展应用
     *
     * @param myOkHttpResponseHandler
     */
    public void getExpandList(MyOkHttpResponseHandler myOkHttpResponseHandler) {
        if (BaseApplication.getUseBeen() == null) {
            ToastUtils.showShortToast("请先登录");
            return;
        }
        try {
            requestPackage.put(uid, BaseApplication.getUseBeen().getId());
            sendPostRequest(URL.HOST_URL, null, requestPackage, myOkHttpResponseHandler);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取表情
     *
     * @param myOkHttpResponseHandler
     */
    public void getFaces(MyOkHttpResponseHandler myOkHttpResponseHandler) {
        if (BaseApplication.getUseBeen() == null) {
            ToastUtils.showShortToast("请先登录");
            return;
        }
        try {
            requestPackage.put(uid, BaseApplication.getUseBeen().getId());
            sendPostRequest(URL.HOST_URL, null, requestPackage, myOkHttpResponseHandler);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取滤镜
     *
     * @param myOkHttpResponseHandler
     */
    public void getFilters(MyOkHttpResponseHandler myOkHttpResponseHandler) {
        if (BaseApplication.getUseBeen() == null) {
            ToastUtils.showShortToast("请先登录");
            return;
        }
        try {
            requestPackage.put(uid, BaseApplication.getUseBeen().getId());
            sendPostRequest(URL.HOST_URL, null, requestPackage, myOkHttpResponseHandler);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getAppVersion(MyOkHttpResponseHandler myOkHttpResponseHandler) {
        try {
            requestPackage.put(versioncode, Constant.VERSIONCODE);
            sendPostRequest(URL.HOST_URL, null, requestPackage, myOkHttpResponseHandler);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 邮箱注册
     */
    public void emailRegister(String userName, String passWord, String nickname,
                              MyOkHttpResponseHandler myOkHttpResponseHandler) {
        try {
            requestPackage.put(username, userName);
            requestPackage.put(password, MD5Utils.getMd5(passWord));
            requestPackage.put(alias, nickname);
            requestPackage.put(registertype, String.valueOf(1));
            sendPostRequest(URL.HOST_URL, null, requestPackage, myOkHttpResponseHandler);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 手机号注册
     *
     * @param userName
     * @param passWord
     * @param code
     * @param nickname
     * @param registerType
     * @param myOkHttpResponseHandler
     */
    public void register(String userName, String passWord, String code, String nickname, int registerType,
                         MyOkHttpResponseHandler myOkHttpResponseHandler) {
        try {
            requestPackage.put(username, userName);
            requestPackage.put(password, MD5Utils.getMd5(passWord));
            requestPackage.put(authcode, MD5Utils.getMd5(code));
            requestPackage.put(alias, nickname);
            requestPackage.put(registertype, String.valueOf(registerType));
            sendPostRequest(URL.HOST_URL, null, requestPackage, myOkHttpResponseHandler);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取验证码
     *
     * @param name
     * @param myOkHttpResponseHandler
     */
    public void getCode(String name, MyOkHttpResponseHandler myOkHttpResponseHandler) {
        try {
            requestPackage.put(username, name);
            sendPostRequest(URL.HOST_URL, null, requestPackage, myOkHttpResponseHandler);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录
     *
     * @param name
     * @param pwd
     * @param myOkHttpResponseHandler
     */
    public void login(String name, String pwd, MyOkHttpResponseHandler myOkHttpResponseHandler) {
        String mPwd = MD5Utils.getMd5(pwd);
        try {
            requestPackage.put(username, name);
            requestPackage.put(password, mPwd);
            sendPostRequest(URL.HOST_URL, null, requestPackage, myOkHttpResponseHandler);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
