package com.example.a719equipmentmanagement.net;


import com.example.a719equipmentmanagement.entity.ApplyHistory;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.BorrowHistory;
import com.example.a719equipmentmanagement.entity.Container;
import com.example.a719equipmentmanagement.entity.ContainerData;
import com.example.a719equipmentmanagement.entity.DeviceDetailData;
import com.example.a719equipmentmanagement.entity.DeviceClassifiy;
import com.example.a719equipmentmanagement.entity.DeviceData2;
import com.example.a719equipmentmanagement.entity.DeviceScanData;
import com.example.a719equipmentmanagement.entity.DictData;
import com.example.a719equipmentmanagement.entity.HandleHistory;
import com.example.a719equipmentmanagement.entity.InRecordData;
import com.example.a719equipmentmanagement.entity.Inventories;
import com.example.a719equipmentmanagement.entity.InventoryHistory;
import com.example.a719equipmentmanagement.entity.Me;
import com.example.a719equipmentmanagement.entity.MsgData;
import com.example.a719equipmentmanagement.entity.ReviewHistory;
import com.example.a719equipmentmanagement.entity.RoleData;
import com.example.a719equipmentmanagement.entity.ToAudit;
import com.example.a719equipmentmanagement.entity.ToDo;
import com.example.a719equipmentmanagement.entity.ToReturn;
import com.example.a719equipmentmanagement.entity.TreeData;
import com.example.a719equipmentmanagement.entity.DeptList;
import com.example.a719equipmentmanagement.entity.InvalidEquip;
import com.example.a719equipmentmanagement.entity.UserData;
import com.example.a719equipmentmanagement.entity.UserToAudit;

import java.io.File;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 *
 */
public interface ApiService {

    /*------  login-controller -------*/
    //APP登录
    @FormUrlEncoded
    @POST("loginByApp")
    Single<BaseResponse> login(@Field("username") String username,
                               @Field("password") String password);

    //APP用户退出
    @GET("logoutByApp")
    Single<BaseResponse> loginout();


    /*------  category-controller -------*/
    //添加设备分类
    @POST("system/category/add")
    Single<BaseResponse> addDeviceType(@Body RequestBody requestBody);


    //删除设备分类
    @FormUrlEncoded
    @POST("system/category/del")
    Single<BaseResponse> deleteDeviceType(@Field("id") int id);

    //查找设备分类数据
    @GET("system/category/list")
    Single<List<DeviceClassifiy>> findDeviceTypeData();

    //更新设备分类数据
    @POST("system/category/update")
    Single<BaseResponse> updataDeviceType(@Body RequestBody requestBody);

    //更新设备分类数据
    @FormUrlEncoded
    @POST("system/equip/scan")
    Single<DeviceScanData> getScanData(@Field("id") String id);


    /*------  container-controller -------*/
    //添加货柜
    @POST("system/container/add")
    Single<BaseResponse> addContainer(@Body RequestBody requestBody);

    //批量添加货柜
    @POST("system/container/addBatch")
    Single<BaseResponse> addBatchContainer(@Body RequestBody requestBody);

    //编辑货柜
    @POST("system/container/update")
    Single<BaseResponse> editContainer(@Body RequestBody requestBody);

    //查找货柜数据
    @GET("system/container/list")
    Single<List<ContainerData>> findContainerData();

    //删除货柜
    @FormUrlEncoded
    @POST("system/container/del")
    Single<BaseResponse> deleteContainer(@Field("id") int id);


    /*------  dept-controller -------*/
    //添加科室
    @FormUrlEncoded
    @POST("system/dept/add")
    Single<BaseResponse> addDept(
            @FieldMap Map<String, Object> map
    );

    //校验部门名称
    @POST("system/dept/checkDeptNameUnique")
    Single<BaseResponse> matchDept();

    //查找科室人员数据
    @GET("system/dept/deptlist")
    Single<List<DeptList>> getDeptList();

    //编辑科室
    @FormUrlEncoded
    @POST("system/dept/edit")
    Single<BaseResponse> editDept(@FieldMap Map<String, Object> map);

    //删除科室
    @GET("system/dept/remove/{deptId}")
    Single<BaseResponse> delete(@Path("deptId") int deptId);

    //获取科室
    @GET("system/dept/treeData")
    Single<List<TreeData>> getTreeData();

    /* -------------- dict-data-controller  ----------------------- */
    //获取数据字典
    @GET("system/dict/data/findByType")
    Single<DictData> findDictData();


    /* -----------------  equip-controller -----------------------*/
    //添加设备
    @POST("system/equip/add")
    Single<BaseResponse> addDevice(@Body RequestBody requestBody);

    //依据id查询设备详情
    @FormUrlEncoded
    @POST("system/equip/get")
    Single<DeviceDetailData> getDeviceDetail(@Field("id") String id);

    //查找设备数据
    @GET("system/equip/list")
    Single<DeviceData2> findDeviceData(@QueryMap Map<String, Object> map);

    //更新设备
    @POST("system/equip/update")
    Single<BaseResponse> updateDevice(@Body RequestBody requestBody);

    //删除设备
    @FormUrlEncoded
    @POST("system/equip/del")
    Single<BaseResponse> deleteDevice(@Field("id") String id);


    /*----------------- inventory-controller ----------------*/
    //新建盘点任务
    @POST("system/inventory/add")
    Single<BaseResponse> newInventoryTask(@Body RequestBody requestBody);

    //设置盘点货柜范围
    @POST("system/inventory/addContainer")
    Single<BaseResponse> setInventoryContainer(@Body RequestBody requestBody);

    //保存盘点设备
    @POST("system/inventory/addDetails")
    Single<BaseResponse> saveInventoryDevice(@Body RequestBody requestBody);

    //设置盘点完成
    @FormUrlEncoded
    @POST("system/inventory/endInventory")
    Single<BaseResponse> setEndInventory(@Field("id") long id);

    //取消盘点任务
    @FormUrlEncoded
    @POST("system/inventory/cancelInventory")
    Single<BaseResponse> cancelInventoryTask(@Field("id") long id);

    //查找当前用户进行中的盘点
    @GET("system/inventory/listMy")
    Single<Inventories> getInventories();

    //查找盘点历史
    @GET("system/user/profile/listMyAllInventory")
    Single<InventoryHistory> findInventoryData(@QueryMap Map<String, Object> map);

    //获取盘点记录id
    @GET("system/inventory/nextId")
    Single<Integer> findInventoryRevordId();

    //查找当前用户处理历史
    @GET("system/user/profile/listMyAllHandle")
    Single<HandleHistory> getHandle(@QueryMap Map<String, Object> map);


    /*----------------- msg-controller  ----------------*/
    //查找消息数据
    @GET("system/msg/list")
    Single<MsgData> findMsgData();


    //管理员：即将过期的设备
    @GET("invalidEquip")
    Single<List<InvalidEquip>> invalidEquip();

    //管理员：我的待审任务
    @GET("toAudit")
    Single<ToAudit> toAudit();

    //管理员: 我的待办事项（送检、解封、报废）
    @GET("toHandle")
    Single<ToDo> toHandle();

    //普通用户: 我的待还设备
    @GET("toReturn")
    Single<ToReturn> toReturn();

    //普通用户: 我的申请进度
    @GET("userToAudit")
    Single<UserToAudit> userToAudit();

    //普通用户: 我的待办事项
    @GET("userToDo")
    Single<ToDo> userToDo();


    /*----------------- profile-controller  ----------------*/
    //APP获取当前用户信息
    @GET("system/user/profile/me")
    Single<Me> getMe();

    //修改密码
    @FormUrlEncoded
    @POST("system/user/profile/resetPwd")
    Single<BaseResponse> editPassword(@Field("oldPassword") String oldPassword,
                                      @Field("newPassword") String newPassword);

    //APP更新个人信息
    @FormUrlEncoded
    @POST("system/user/profile/update")
    Single<BaseResponse> updataUserData(@FieldMap Map<String, Object> map);

    //操作设备
    @FormUrlEncoded
    @POST("system/eqLog/oper")
    Single<BaseResponse> operatingEquip(@FieldMap Map<String, Object> map);

    //保存头像
    @FormUrlEncoded
    @POST("system/user/profile/updateAvatar")
    Single<BaseResponse> saveAvatar(@Field("avatarfile") File avatarfile);


    /*----------------- role-controller  ----------------*/
    //查找角色数据
    @GET("system/role/listByApp")
    Single<RoleData> findRoleData();

    /*----------------- setup-controller  ----------------*/
    //添加建账入库记录
    @POST("system/setup/add")
    Single<BaseResponse> addInRecord(@Body RequestBody requestBody);

    //查找建账入库数据
    @GET("system/setup/list")
    Single<InRecordData> findInRecordData();

    //查找当前用户的审核历史
    @GET("system/user/profile/listMyAllVerify")
    Single<ReviewHistory> findReviewHistory(@QueryMap Map<String, Object> map);

    //查找当前用户的申请历史
    @GET("system/user/profile/listMyAllApply")
    Single<ApplyHistory> findUserApplyHistory(@QueryMap Map<String, Object> map);

    //查找建账入库数据
    @GET("system/setup/nextId")
    Single<Integer> getNextId();

    //查找建账入库数据
    @GET("system/user/profile/listMyAllBorrow")
    Single<BorrowHistory> getBorrowHistory(@QueryMap Map<String, Object> map);

    //查找用户数据
    @GET("system/user/get")
    Single<UserData> getRole(@Query("userId") int userId);

    //查询货柜
    @FormUrlEncoded
    @POST("system/container/get")
    Single<Container> queryContainer(@Field("id") String userId);

    /*----------------- user-controller  ----------------*/
    //新增保存用户
    @FormUrlEncoded
    @POST("system/user/add")
    Single<BaseResponse> addUser(@FieldMap Map<String, Object> map);

    //编辑保存用户
    @FormUrlEncoded
    @POST("system/user/edit")
    Single<BaseResponse> editUser(@FieldMap Map<String, Object> map);

    //删除用户
    @FormUrlEncoded
    @POST("system/user/remove")
    Single<BaseResponse> deleteUser(@Field("ids") int ids);

    //重置密码
    @FormUrlEncoded
    @POST("system/user/resetPwd")
    Single<BaseResponse> resetPwd(@Field("userId") int userId,
                                  @Field("loginName") String loginName,
                                  @Field("password") String password);

    //重置密码
    @FormUrlEncoded
    @POST("system/user/resetPwd")
    Single<BaseResponse> resetPwd(
            @Field("oldPassword") String oldPassword,
            @Field("newPassword") String newPassword);

    //测试
    @GET("system/user/resetPwd")
    Single<BaseResponse> test(@QueryMap IdentityHashMap<String, String> map);


}
