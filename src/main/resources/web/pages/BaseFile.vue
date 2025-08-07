<template>
  <div class="app-container">
    <div class="search-container">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true">
        <el-form-item label="用户名" prop="name">
          <el-input
              v-model="queryParams.selectName"
              placeholder="用户名、昵称"
              clearable
              @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button v-hasPermission="['baseFile:query']" type="primary" icon="Search" @click="handleQuery">搜索</el-button>
          <el-button icon="Refresh" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-card shadow="never" class="table-container" >
      <template #header>
        <el-row :gutter="10">
          <div style="
              font-size: 12px;
              z-index: 20;
              border: 2px dashed #409eff;
              border-radius: 5px;
              padding: 3px 3px;">
            勾选 {{ uids.length }} 条(可跨页)
            <el-button
                size="small"
                style="z-index: 20"
                type="primary"
                @click="handelClearSelection"
            >清空</el-button>
          </div>
          <el-col :span="8">
            <el-button v-hasPermission="['baseFile:add']" type="primary" icon="Plus" @click="openDialog(null)">新增</el-button>
            <el-button v-hasPermission="['baseFile:delete']" type="danger" icon="Delete" @click="handleDelete(null)">删除</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table
          ref="tableRef"
          :height="scrollerHeight"
          v-loading="loading"
          highlight-current-row
          :data="tableData"
          border
          stripe
          lazy
          row-key="uid"
          :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
          @selection-change="handleSelectionChange"
      >
        <el-table-column fixed="left" type="selection" width="55" align="center"/>
        <el-table-column label="显示名" prop="showName" width="100" align="center"/>
        <el-table-column label="文件名" prop="fileName" width="100" align="center"/>
        <el-table-column label="文件路径" prop="filePath" width="100" align="center"/>
        <el-table-column label="dfs文件路径" prop="dfsPath" width="100" align="center"/>
        <el-table-column label="临时文件标识（0 否   1是）" prop="tempFlag" width="100" align="center"/>
        <el-table-column label="文件类型（0 通用文件  ）" prop="fileType" width="100" align="center"/>
        <el-table-column label="创建人uid" prop="createUserUid" width="100" align="center"/>
        <el-table-column label="创建时间" prop="createTime" width="100" align="center"/>
        <el-table-column label="修改人uid" prop="updateUserUid" width="100" align="center"/>
        <el-table-column label="修改时间" prop="updateTime" width="100" align="center"/>
        <el-table-column fixed="right" label="操作" align="center" width="220">
          <template #default="scope">
            <el-button
                v-hasPermission="['baseFile:update']"
                type="primary"
                link
                size="small"
                @click.stop="openDialog(scope.row)"
            >
              <i-ep-edit/>
              编辑
            </el-button>
            <el-button
                v-hasPermission="['baseFile:delete']"
                type="primary"
                link
                size="small"
                :disabled="scope.row.userName === 'admin'"
                @click.stop="handleDelete(scope.row.uid)"
            >
              <i-ep-delete/>
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination
          v-model:total="pageInfo.total"
          v-model:page="pageInfo.current"
          v-model:limit="pageInfo.size"
          @pagination="handleQuery"
      />
    </el-card>

    <el-dialog
        v-model="dialog.visible"
        :title="dialog.title"
        width="600px"
        @close="dialogClose">
      <el-form
          ref="dialogFormRef"
          :model="dialogForm"
          label-width="auto"
          :rules="rules"

      >
       
    <el-row :gutter="20">

  <el-col :span="12">
    <el-form-item label="显示名" prop="showName">
        <el-input v-model="dialogForm.showName" placeholder="请输入显示名" />
    </el-form-item>
  </el-col>


  <el-col :span="12">
    <el-form-item label="文件名" prop="fileName">
        <el-input v-model="dialogForm.fileName" placeholder="请输入文件名" />
    </el-form-item>
  </el-col>

    </el-row>
    <el-row :gutter="20">

  <el-col :span="12">
    <el-form-item label="文件路径" prop="filePath">
        <el-input v-model="dialogForm.filePath" placeholder="请输入文件路径" />
    </el-form-item>
  </el-col>


  <el-col :span="12">
    <el-form-item label="dfs文件路径" prop="dfsPath">
        <el-input v-model="dialogForm.dfsPath" placeholder="请输入dfs文件路径" />
    </el-form-item>
  </el-col>

    </el-row>
    <el-row :gutter="20">

  <el-col :span="12">
    <el-form-item label="临时文件标识（0 否   1是）" prop="tempFlag">
        <el-select v-model="dialogForm.tempFlag" placeholder="请选择临时文件标识（0 否   1是）" style="width: 100%;">
          <el-option label="是" :value="1"/>
          <el-option label="否" :value="0"/>
        </el-select>
    </el-form-item>
  </el-col>


  <el-col :span="12">
    <el-form-item label="文件类型（0 通用文件  ）" prop="fileType">
        <el-select v-model="dialogForm.fileType" placeholder="请选择文件类型（0 通用文件  ）" style="width: 100%;">
          <el-option label="是" :value="1"/>
          <el-option label="否" :value="0"/>
        </el-select>
    </el-form-item>
  </el-col>

    </el-row>
    <el-row :gutter="20">

  <el-col :span="12">
    <el-form-item label="创建人uid" prop="createUserUid">
        <el-input v-model="dialogForm.createUserUid" placeholder="请输入创建人uid" />
    </el-form-item>
  </el-col>


  <el-col :span="12">
    <el-form-item label="创建时间" prop="createTime">
        <el-date-picker v-model="dialogForm.createTime" type="date" placeholder="请选择创建时间" style="width: 100%;"/>
    </el-form-item>
  </el-col>

    </el-row>
    <el-row :gutter="20">

  <el-col :span="12">
    <el-form-item label="修改人uid" prop="updateUserUid">
        <el-input v-model="dialogForm.updateUserUid" placeholder="请输入修改人uid" />
    </el-form-item>
  </el-col>


  <el-col :span="12">
    <el-form-item label="修改时间" prop="updateTime">
        <el-date-picker v-model="dialogForm.updateTime" type="date" placeholder="请选择修改时间" style="width: 100%;"/>
    </el-form-item>
  </el-col>

    </el-row>



        
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="handleSubmit"> 确 定 </el-button>
          <el-button @click="dialogClose"> 取 消 </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import {PageInfo, Result} from "@/api/common/ResultType.ts"
import {getStorageData} from "@/hooks/common";
import {Token} from "@/api/common/Token.ts"
import {
  BaseFile,
  getBaseFileByPage,
  deleteBaseFile,
  addBaseFile,
  updateBaseFile,
} from "@/api/base/BaseFileApi.ts";
import {getSysOrganizationByProperty, SysOrganization} from "@/api/system/SysOrganizationApi.ts";
import {getSysRoleByProperty, SysRole} from "@/api/system/SysRoleApi.ts";
import {FormInstance} from "element-plus";

const tokenStore : Token = getStorageData('token')
let queryFormRef = ref(ElForm);
let loading = ref(false);
let tableRef = ref()
let uids = ref<Array<string>>([]);
let tableData = ref<BaseFile[]>();
let queryParams = reactive<BaseFile>( {
  uid: null,
  showName: null,
  fileName: null,
  filePath: null,
  dfsPath: null,
  tempFlag: null,
  fileType: null,
  createUserUid: null,
  createTime: null,
  updateUserUid: null,
  updateTime: null,
});
let pageInfo = reactive<PageInfo<BaseFile>>({
  pages: 0, records: [], searchCount: false, total: 0,
  current : 1,
  size : 10
});

const dialogFormRef = ref<FormInstance>();
let dialog = reactive({
  visible: false,
  title: '',
  type: ''
})
let dialogForm = reactive<BaseFile> ( {
  uid: null,
  showName: null,
  fileName: null,
  filePath: null,
  dfsPath: null,
  tempFlag: null,
  fileType: null,
  createUserUid: null,
  createTime: null,
  updateUserUid: null,
  updateTime: null,
});





const rules = reactive({
  
})
let allOrganization = ref<Array<SysOrganization>>([]);
let allRole = ref<Array<SysRole>>([]);

/** 行复选框选中  */
function handleSelectionChange(selection: any) {
  uids.value = selection.map((item: any) => item.uid);
}

/** 清空行复选 */
function handelClearSelection () {
  tableRef.value.clearSelection()
}

/**
 * 查询
 */
function handleQuery() {
  getBaseFileByPage(pageInfo, queryParams)
      .then((res :  Result<PageInfo<BaseFile>>) => {
        if (res.status == 0) {
          tableData.value = res.data.records
          pageInfo.total = res.data.total
          pageInfo.current = res.data.current
          pageInfo.size = res.data.size
        } else {
          ElMessage.error(res.msg)
        }
      }).catch((err) => {
    console.log(err)
  }) ;
}

/**
 * 重置查询
 */
function resetQuery() {
  let newQueryParams = reactive<BaseFile>( {
  uid: null,
  showName: null,
  fileName: null,
  filePath: null,
  dfsPath: null,
  tempFlag: null,
  fileType: null,
  createUserUid: null,
  createTime: null,
  updateUserUid: null,
  updateTime: null,

  });
  let newPageInfo = reactive<PageInfo<BaseFile>>({
    pages: 0, records: [], searchCount: false, total: 0,
    current : 1,
    size : 10
  });
  Object.assign(queryParams, newQueryParams)
  Object.assign(pageInfo, newPageInfo)
  handleQuery();
}

/**
 * 打开新增、编辑对话框
 */
function openDialog(row : BaseFile | null) {
  if (row) {
    dialog.title = '编辑';
    dialog.type = 'edit';
    Object.assign(dialogForm, row)
  } else {
    dialog.title = '新增';
    dialog.type = 'add';
  }
  dialog.visible = true;
}


/**
 * 删除
 */
function handleDelete(uid?: string | null) {
  // const dictTypeIds = [dictTypeId || uids.value].join(",");
  let delList : Array<string> = []

  if (uid != null) {
    delList.push(uid)
  } else {
    uids.value.forEach(item => delList.push(item))
  }
  if (!delList) {
    ElMessage.warning("请选择删除数据");
    return;
  }
  let data = {
    uidList: delList,
    updateUserUid: tokenStore.userUid
  }
  ElMessageBox.confirm(`已选中${delList.length}个数据，确认删除已选中的数据项?`, "警告", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  }).then(() => {
    deleteBaseFile(data)
        .then(res => {
          if (res.status == 0) {
            ElMessage.success(res.msg)
            handleQuery()
          } else {
            ElMessage.error(res.msg)
          }
        })
        .catch(err => {
          console.log(err)
        });
  });
}

let pageHeight = 0;
let scrollerHeight = 0;
onBeforeMount(() => {
  pageHeight = window.innerHeight;
  scrollerHeight = pageHeight - 84 - 62 - 52 - 40 - 56;
  window.addEventListener("resize", () => {
    pageHeight = window.innerHeight;
    scrollerHeight = pageHeight - 84 - 62 - 52 - 40 - 56;
  });
});

/**
 * 获取所有组织
 */
function getAllOrganization() {
  getSysOrganizationByProperty( {})
      .then((res) => {
        allOrganization = res.data;
      }).catch((err) => {
    console.log(err)
  })
}

/**
 * 获取所有角色
 */
function getAllRole() {
  getSysRoleByProperty( {})
      .then((res) => {
        allRole = res.data;
      }).catch((err) => {
    console.log(err)
  })
}

onMounted(() => {
  handleQuery();
  // getAllPermissionTree();
  if (allOrganization.value?.length === 0) {
    getAllOrganization()
  }
  if (allRole.value?.length === 0) {
    getAllRole()
  }
})

/** 关闭对话框 */
function dialogClose() {
  dialog.visible = false;
  resetForm();
}


/** 重置弹窗表单 */
function resetForm() {
  let newDialogForm = reactive<BaseFile> ( {
  uid: null,
  showName: null,
  fileName: null,
  filePath: null,
  dfsPath: null,
  tempFlag: null,
  fileType: null,
  createUserUid: null,
  createTime: null,
  updateUserUid: null,
  updateTime: null,
  });
  Object.assign(dialogForm, newDialogForm)
}

/**
 * 新增/修改 提交
 */
function handleSubmit() {
  if (dialog.type == 'add') {
    dialogForm.createUserUid = tokenStore.userUid
    addBaseFile(dialogForm)
        .then((res: Result<null>) => {
          if (res.status == 0) {
            ElMessage.success(res.msg)
            dialogClose()
            handleQuery()
          } else {
            ElMessage.error(res.msg)
          }
        })
        .catch((err) => {
          console.log(err)
        });
  } else {
    dialogForm.updateUserUid = tokenStore.userUid
    updateBaseFile(dialogForm)
        .then((res: Result<null>) => {
          if (res.status == 0) {
            ElMessage.success(res.msg)
            dialogClose()
            handleQuery()
          } else {
            ElMessage.error(res.msg)
          }
        })
        .catch((err) => {
          console.log(err)
        });
  }
}


</script>

<style lang="scss" scoped>
.avatar-uploader .avatar {
  width: 75px;
  height: 105px;
  display: block;

}

:deep(.el-upload) {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);

}

:deep(.el-upload:hover) {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 15px;
  color: #8c939d;
  width: 75px;
  height: 105px;
  text-align: center;
}
</style>

