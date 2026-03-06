<template>
  <div class="container">
    <div class="addBlog">
      <Card title="发布博客">
        <Form ref="form" :model="articleParam" :rules="rules" label-width="80">
          <FormItem label="标题" prop="title">
            <Input v-model="articleParam.title" placeholder="请输入文章标题"/>
          </FormItem>
          <FormItem label="内容" prop="content">
            <mavon-editor v-model="articleParam.content" :toolbars="toolbars"/>
          </FormItem>
          <FormItem label="类型">
            <Select v-model="articleParam.type" placeholder="请选择文章类型">
              <Option label="原创" value="0"></Option>
              <Option label="转载" value="1"></Option>
              <Option label="翻译" value="2"></Option>
            </Select>
          </FormItem>
          <FormItem label="标签">
            <Input v-model="articleParam.tags" placeholder="请输入标签，多个标签用逗号分隔"/>
          </FormItem>
          <FormItem label="描述">
            <Input v-model="articleParam.description" placeholder="请输入文章描述"/>
          </FormItem>
          <FormItem label="封面">
            <Input v-model="articleParam.cover" placeholder="请输入封面图片URL"/>
          </FormItem>
          <FormItem label="状态">
            <Select v-model="articleParam.status" placeholder="请选择文章状态">
              <Option label="发布" value="0"></Option>
              <Option label="草稿" value="1"></Option>
            </Select>
          </FormItem>
          <FormItem>
            <Button type="primary" @click="submit" :loading="isLoading">发布文章</Button>
            <Button @click="reset" style="margin-left: 8px;">重置</Button>
          </FormItem>
        </Form>
      </Card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'addBlog',
  data() {
    return {
      articleParam: {
        title: '',
        content: '',
        type: '0',
        tags: '',
        description: '',
        cover: '',
        status: '0'
      },
      rules: {
        title: [
          { required: true, message: '请输入文章标题', trigger: 'blur' },
          { min: 3, max: 100, message: '标题长度在 3 到 100 个字符', trigger: 'blur' }
        ],
        content: [
          { required: true, message: '请输入文章内容', trigger: 'blur' }
        ]
      },
      toolbars: {
        bold: true,
        italic: true,
        header: true,
        underline: true,
        strikethrough: true,
        mark: true,
        superscript: true,
        subscript: true,
        quote: true,
        ol: true,
        ul: true,
        link: true,
        imagelink: true,
        code: true,
        table: true,
        fullscreen: true,
        readmodel: true,
        htmlcode: true,
        help: true,
        undo: true,
        redo: true,
        trash: true,
        save: true,
        navigation: true,
        alignleft: true,
        aligncenter: true,
        alignright: true,
        subfield: true,
        preview: true
      },
      isLoading: false
    }
  },
  methods: {
    submit() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (!this.articleParam.title || this.articleParam.title.trim() === '') {
            this.$Message.error('请输入文章标题')
            return
          }
          if (!this.articleParam.content || this.articleParam.content.trim() === '') {
            this.$Message.error('请输入文章内容')
            return
          }
          this.isLoading = true
          this.axios.post('/articleController/add', this.articleParam, {
            params: {
              token: localStorage.getItem('token')
            }
          }).then((res) => {
            if (res.data.status === 999) {
              this.$Message.error(res.data.msg || '请求失败')
            } else if (res.data.status === 1000) {
              this.$Message.error(res.data.msg || '温馨提示')
            } else if (res.data.status === 200) {
              this.$Message.success('文章发布成功')
              this.$router.push('/home')
            }
          }).catch((error) => {
            this.$Message.error('文章发布失败，请稍后重试')
            console.error(error)
          }).finally(() => {
            this.isLoading = false
          })
        } else {
          this.$Message.error('表单验证失败，请检查输入')
        }
      })
    },
    reset() {
      this.$refs.form.resetFields()
      this.articleParam = {
        title: '',
        content: '',
        type: '0',
        tags: '',
        description: '',
        cover: '',
        status: '0'
      }
    }
  }
}
</script>

<style scoped>
.addBlog {
  margin: 20px;
}
.ivu-card {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.ivu-form-item {
  margin-bottom: 20px;
}
.ivu-btn {
  margin-right: 8px;
}
</style>
