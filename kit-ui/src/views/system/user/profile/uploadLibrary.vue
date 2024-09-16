<template>
  <div>
    <input type="file" @change="uploadImg">
  </div>
</template>
<!---->
<script>
  import store from "@/store";
  import {VueCropper} from "vue-cropper";
  import {uploadLibrary} from "@/api/library/library";

  export default {
    props: {
      simple: {
        type: Boolean,
        required: true,
        default: false
      }
    },
    data() {
      return {
        fileList: []
      };
    },
    methods: {
      uploadImg(event) {
        let formData = new FormData();
        for (let index = 0; index < event.target.files.length; index++) {
          formData.append("file", event.target.files[index], event.target.files[index].name);
        }
        uploadLibrary(formData).then(response => {
          this.$modal.msgSuccess("上传成功");
        });
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${file.name}？`);
      },
      handleChange(file, fileList) {
        this.fileList = fileList.slice(-3);
      }
    }
  }
</script>


<style scoped>

</style>
