<template>
  <div class="container">
    <div class="register" style="position:absolute;top:35%;left:50%;transform: translate(-50%,-50%);">
      <Col align="center">
        <h1>注册账户</h1>
      </Col>
      <Card align="center" style="width: 450px">
        <Form ref="userInfo" :model="userInfo" :rules="userInfoRules">
          <FormItem prop="username">
            <Input type="text" v-model="userInfo.username" placeholder="请输入用户名">
              <span slot="prepend">
                <Icon :size="16" type="ios-person"/>
              </span>
            </Input>
          </FormItem>
          <FormItem prop="password">
            <Input type="password" v-model="userInfo.password" placeholder="请输入密码长度在5到25个字符,支持字母、数字、下划线">
              <span slot="prepend">
                <Icon :size="14" type="md-lock"/>
              </span>
            </Input>
          </FormItem>
          <FormItem prop="email">
            <Input type="text" v-model="userInfo.email" placeholder="请输入邮箱号码">
              <span slot="prepend">
                <Icon type="ios-mail" :size="16"/>
              </span>
            </Input>
          </FormItem>
          <FormItem prop="phone">
            <Input type="text" v-model="userInfo.phone" placeholder="请输入手机号码">
              <span slot="prepend">
                <Icon type="md-phone-portrait" :size="16"/>
              </span>
            </Input>
          </FormItem>
          <FormItem>
            <Button @click="register('userInfo')" long type="primary">注册</Button>
          </FormItem>
        </Form>
      </Card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'register',
  data() {
    return {
      userInfo: {
        username: '',
        password: '',
        email: '',
        phone: ''
      },
      userInfoRules: {
        username: [
          { required: true, message: '用户名不能为空', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { min: 5, max: 25, message: '密码长度在5到25个字符', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '邮箱不能为空', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱格式', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '手机号不能为空', trigger: 'blur' },
          { pattern: /^1[3-9]\d{9}$/, message: '请输入正确的手机号格式', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    register(name) {
      this.$refs[name].validate(valid => {
        if (valid) {
          this.axios({
            method: 'post',
            url: '/userController/register',
            data: this.userInfo
          }).then(res => {
            if (res.data.status === 200) {
              this.$Message.success('注册成功')
              this.$router.push('/login')
            } else {
              this.$Message.error(res.data.message || '注册失败')
            }
          }).catch(error => {
            this.$Message.error('注册失败，请稍后重试')
            console.error(error)
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.register {
  width: 100%;
}
</style>
