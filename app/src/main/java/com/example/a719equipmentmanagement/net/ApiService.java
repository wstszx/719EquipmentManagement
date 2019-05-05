package com.example.a719equipmentmanagement.net;


import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.ContainerData;
import com.example.a719equipmentmanagement.entity.CurrentUserData;
import com.example.a719equipmentmanagement.entity.DeviceData;
import com.example.a719equipmentmanagement.entity.DeviceTypeData;
import com.example.a719equipmentmanagement.entity.DictData;
import com.example.a719equipmentmanagement.entity.InRecordData;
import com.example.a719equipmentmanagement.entity.InventoryData;
import com.example.a719equipmentmanagement.entity.InventoryRevordId;
import com.example.a719equipmentmanagement.entity.LoginBean;
import com.example.a719equipmentmanagement.entity.MsgData;
import com.example.a719equipmentmanagement.entity.RoleData;
import com.example.a719equipmentmanagement.entity.User;
import com.example.a719equipmentmanagement.entity.UserListData;

import java.io.File;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 *
 */
public interface ApiService {

//    //登陆
//    @POST("login")
//    Call<LoginBean> login(@Body RequestBody requestBody);
//
//    //注册
//    @FormUrlEncoded
//    @POST("user/register/")
//    Call<LoginBean> register(@Field("username") String username,
//                             @Field("password") String password,
//                             @Field("repassword") String repassword);


    /*------  login-controller -------*/
    //APP登录
    @FormUrlEncoded
    @POST("loginByApp")
    Call<LoginBean> login(@Field("username") String username,
                          @Field("password") String password);
    //APP用户退出
    @GET("logoutByApp")
    Call<BaseResponse> loginout();



    /*------  category-controller -------*/
    //添加设备分类
    @POST("system/category/add")
    Call<BaseResponse> addDeviceType(@Body RequestBody requestBody);

    //查找设备分类数据
    @GET("system/category/list")
    Call<DeviceTypeData> findDeviceTypeData();



    /*------  container-controller -------*/
    //添加货柜
    @POST("system/container/add")
    Call<BaseResponse> addContainer(@Body RequestBody requestBody);
    //查找货柜数据
    @GET("system/container/list")
    Call<ContainerData> findContainerData();



    /*------  dept-controller -------*/
    //添加科室
    @POST("system/dept/add")
    Call<BaseResponse> addDept();  //  ?????????

    //校验部门名称
    @POST("system/dept/checkDeptNameUnique")
    Call<BaseResponse> matchDept();  //????????

    //查找科室人员数据
    @GET("system/dept/deptlist")
    Call<List<User>> getUser();

    //编辑科室
    @POST("system/dept/edit")
    Call<BaseResponse> edit();

    //删除科室
    @GET("system/dept/remove/{deptId}")
    Call<BaseResponse> delete();


    /* -------------- dict-data-controller  ----------------------- */
    //获取数据字典
    @GET("system/dict/data/findByType")
    Call<DictData> findDictData();


    /* -----------------  equip-controller -----------------------*/
    //添加设备
    @POST("system/equip/add")
    Call<BaseResponse> addDevice(@Body RequestBody requestBody);
    //查找设备数据
    @GET("system/equip/list")
    Call<DeviceData> findDeviceData();


    /*----------------- inventory-controller ----------------*/
    //设置盘点货柜范围
    @POST("system/inventory/addContainer")
    Call<BaseResponse> setInventoryContainer(@Body RequestBody requestBody);
    //保存盘点设备
    @POST("system/inventory/addDetails")
    Call<BaseResponse> saveInventoryDevice(@Body RequestBody requestBody);
    //设置盘点结束
    @POST("system/inventory/endInventory")
    Call<BaseResponse> setEndInventory(@Body RequestBody requestBody);
    //查找盘点数据
    @GET("system/inventory/list")
    Call<InventoryData> findInventoryData();
    //获取盘点记录id
    @GET("system/inventory/nextId")
    Call<InventoryRevordId> findInventoryRevordId();



    /*----------------- msg-controller  ----------------*/
    //查找消息数据
    @GET("system/msg/list")
    Call<MsgData> findMsgData();


    /*----------------- profile-controller  ----------------*/
    //APP获取当前用户信息
    @GET("system/user/profile/me")
    Call<User> getMe();
    //修改密码
    @POST("system/user/profile/resetPwd")
    Call<BaseResponse> editPassword(@Field("oldPassword") String oldPassword,
                                    @Field("newPassword") String newPassword);
    //APP更新个人信息
    @POST("system/user/profile/update")
    Call<BaseResponse> updataUserData();
    //保存头像
    @POST("system/user/profile/updateAvatar")
    Call<BaseResponse> saveAvatar(@Field("avatarfile") File avatarfile);


    /*----------------- role-controller  ----------------*/
    //查找角色数据
    @GET("system/role/listByApp")
    Call<RoleData> findRoleData();




    /*----------------- setup-controller  ----------------*/
    //添加建账入库记录
    @POST("system/setup/add")
    Call<BaseResponse> addInRecord(@Body RequestBody requestBody);
    //查找建账入库数据
    @GET("system/setup/list")
    Call<InRecordData> findInRecordData();


//    /*----------------- test-controller  ----------------*/
//    //删除用户
//    @DELETE("test/delete")
//    Call<BaseResponse> deleteUser(@Body RequestBody requestBody);
//    //获取列表
//    @GET("test/list")
//    Call<UserListData> findUserListData();
//    //新增用户
//    @POST("test/save")
//    Call<BaseResponse> addUser(@Field("userId") String userId,
//                               @Field("username") String username,
//                               @Field("password") String password);
//    //更新用户
//    @PUT("test/update")
//    Call<BaseResponse> updataUser(@Body RequestBody requestBody);


    /*----------------- user-controller  ----------------*/
    //新增保存用户
    @POST("system/user/add")
    Call<BaseResponse> addUser();
    //编辑保存用户
    @POST("system/user/edit")
    Call<BaseResponse> editUser();
    //删除用户
    @POST("system/user/remove")
    Call<BaseResponse> deleteUser(@Field("ids") String ids);
    //重置密码
    @POST("system/user/resetPwd")
    Call<BaseResponse> resetPwd();

}
