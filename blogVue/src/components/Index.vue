<template>
  <div class="layout">
    <!-- 顶部导航栏 -->
    <header class="header">
      <div class="header-container">
        <!-- Logo -->
        <div class="logo" @click="goHome">
          <img src="https://images.unsplash.com/photo-1517841905240-472988babdf9?w=40&h=40&fit=crop" alt="logo" class="logo-img"/>
          <span class="logo-text">糖菓部落</span>
        </div>

        <!-- 桌面端菜单 -->
        <nav class="nav-menu desktop-menu">
          <a 
            v-for="item in menuItems" 
            :key="item.name"
            :class="['nav-item', { active: activeName === item.name }]"
            @click="select(item.name)"
          >
            {{ item.label }}
          </a>
        </nav>

        <!-- 右侧操作区 -->
        <div class="header-actions">
          <!-- 搜索按钮 -->
          <button class="action-btn search-btn">
            <i class="iconfont icon-search"></i>
          </button>

          <!-- 登录/用户 -->
          <div v-if="loginBefore" class="login-wrapper">
            <button class="login-btn" @click="gotoLogin">
              <i class="iconfont icon-user"></i>
              <span>登录</span>
            </button>
          </div>
          
          <Dropdown v-else class="user-dropdown" @on-click="dropdownMethod">
            <button class="user-btn">
              <img src="https://images.unsplash.com/photo-1517841905240-472988babdf9?w=32&h=32&fit=crop" class="user-avatar"/>
              <span class="user-name">{{ username }}</span>
              <i class="iconfont icon-arrow-down"></i>
            </button>
            <DropdownMenu slot="list">
              <DropdownItem name="userInfo">
                <i class="iconfont icon-user"></i> 个人主页
              </DropdownItem>
              <DropdownItem name="releaseBlog">
                <i class="iconfont icon-edit"></i> 发布博客
              </DropdownItem>
              <DropdownItem name="blogManagement">
                <i class="iconfont icon-manage"></i> 博客管理
              </DropdownItem>
              <DropdownItem divided name="logout">
                <i class="iconfont icon-logout"></i> 退出登录
              </DropdownItem>
            </DropdownMenu>
          </Dropdown>

          <!-- 移动端菜单按钮 -->
          <button class="mobile-menu-btn" @click="toggleMobileMenu">
            <i class="iconfont" :class="showMobileMenu ? 'icon-close' : 'icon-menu'"></i>
          </button>
        </div>
      </div>

      <!-- 移动端菜单 -->
      <transition name="slide-down">
        <div v-show="showMobileMenu" class="mobile-menu">
          <a 
            v-for="item in menuItems" 
            :key="item.name"
            :class="['mobile-nav-item', { active: activeName === item.name }]"
            @click="select(item.name)"
          >
            {{ item.label }}
          </a>
          <div v-if="loginBefore" class="mobile-login">
            <button class="mobile-login-btn" @click="gotoLogin">登录 / 注册</button>
          </div>
        </div>
      </transition>
    </header>

    <!-- 主内容区 -->
    <main class="main-content">
      <router-view/>
    </main>

    <!-- 页脚 -->
    <footer class="footer">
      <div class="footer-container">
        <div class="footer-content">
          <div class="footer-section">
            <h4 class="footer-title">糖菓部落</h4>
            <p class="footer-desc">分享技术、记录生活、探索二次元</p>
            <div class="social-links">
              <a href="#" class="social-link"><i class="iconfont icon-github"></i></a>
              <a href="#" class="social-link"><i class="iconfont icon-twitter"></i></a>
              <a href="#" class="social-link"><i class="iconfont icon-email"></i></a>
            </div>
          </div>
          <div class="footer-section">
            <h4 class="footer-title">快速链接</h4>
            <ul class="footer-links">
              <li><a @click="select('home')">首页</a></li>
              <li><a @click="select('openSourceBooks')">开源书籍</a></li>
              <li><a @click="select('openSourceProject')">开源项目</a></li>
            </ul>
          </div>
          <div class="footer-section">
            <h4 class="footer-title">关于</h4>
            <ul class="footer-links">
              <li><a @click="select('about')">关于我</a></li>
              <li><a @click="select('links')">友情链接</a></li>
            </ul>
          </div>
        </div>
        <div class="footer-bottom">
          <p>&copy; 2025 糖菓部落. All rights reserved.</p>
        </div>
      </div>
    </footer>
  </div>
</template>

<script>
export default {
  name: 'Index',
  data() {
    return {
      activeName: 'home',
      loginBefore: true,
      loginAfter: false,
      username: '',
      showMobileMenu: false,
      menuItems: [
        { name: 'home', label: '首页' },
        { name: 'openSourceBooks', label: '开源书籍' },
        { name: 'openSourceProject', label: '开源项目' },
        { name: 'links', label: '友链' },
        { name: 'about', label: '关于' }
      ]
    }
  },
  created() {
    this.checkLoginStatus()
    this.setActiveMenu()
  },
  watch: {
    '$route': function() {
      this.setActiveMenu()
    }
  },
  methods: {
    checkLoginStatus() {
      const token = localStorage.getItem('token')
      const username = localStorage.getItem('username')
      if (token && username) {
        this.loginBefore = false
        this.loginAfter = true
        this.username = username
      }
    },
    setActiveMenu() {
      const path = this.$route.path
      if (path === '/home' || path === '/') {
        this.activeName = 'home'
      } else if (path === '/openSourceBooks') {
        this.activeName = 'openSourceBooks'
      } else if (path === '/openSourceProject') {
        this.activeName = 'openSourceProject'
      } else if (path === '/links') {
        this.activeName = 'links'
      } else if (path === '/about') {
        this.activeName = 'about'
      }
    },
    select(name) {
      this.activeName = name
      this.showMobileMenu = false
      const routeMap = {
        'home': '/home',
        'openSourceBooks': '/openSourceBooks',
        'openSourceProject': '/openSourceProject',
        'links': '/links',
        'about': '/about'
      }
      this.$router.push(routeMap[name] || '/home')
    },
    goHome() {
      this.$router.push('/home')
    },
    gotoLogin() {
      this.showMobileMenu = false
      this.$router.push('/login')
    },
    toggleMobileMenu() {
      this.showMobileMenu = !this.showMobileMenu
    },
    dropdownMethod(name) {
      if (name === 'logout') {
        localStorage.removeItem('token')
        localStorage.removeItem('username')
        localStorage.removeItem('authorId')
        this.loginBefore = true
        this.loginAfter = false
        this.username = ''
        this.$Message.success('退出登录成功')
        this.$router.push('/home')
      } else if (name === 'userInfo') {
        this.$router.push('/userInfo')
      } else if (name === 'releaseBlog') {
        this.$router.push('/addBlog')
      } else if (name === 'blogManagement') {
        this.$router.push('/userInfo')
      }
    }
  }
}
</script>

<style scoped>
/* 布局 */
.layout {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

/* 顶部导航 */
.header {
  background: white;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  position: sticky;
  top: 0;
  z-index: 1000;
}

.header-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
  height: 64px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

/* Logo */
.logo {
  display: flex;
  align-items: center;
  gap: 12px;
  cursor: pointer;
}

.logo-img {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  object-fit: cover;
}

.logo-text {
  font-size: 20px;
  font-weight: 700;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

/* 桌面端菜单 */
.desktop-menu {
  display: flex;
  gap: 8px;
}

.nav-item {
  padding: 8px 20px;
  border-radius: 20px;
  font-size: 15px;
  color: #666;
  cursor: pointer;
  transition: all 0.3s;
  text-decoration: none;
}

.nav-item:hover {
  color: #667eea;
  background: #f5f7fa;
}

.nav-item.active {
  color: white;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

/* 右侧操作区 */
.header-actions {
  display: flex;
  align-items: center;
  gap: 16px;
}

.action-btn {
  width: 40px;
  height: 40px;
  border: none;
  background: transparent;
  border-radius: 50%;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.3s;
}

.action-btn:hover {
  background: #f5f7fa;
}

.action-btn i {
  font-size: 20px;
  color: #666;
}

/* 登录按钮 */
.login-btn {
  display: flex;
  align-items: center;
  gap: 6px;
  padding: 8px 20px;
  border: 1px solid #667eea;
  background: transparent;
  color: #667eea;
  border-radius: 20px;
  cursor: pointer;
  font-size: 14px;
  transition: all 0.3s;
}

.login-btn:hover {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  border-color: transparent;
}

/* 用户下拉 */
.user-btn {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 6px 12px 6px 6px;
  border: none;
  background: #f5f7fa;
  border-radius: 24px;
  cursor: pointer;
  transition: all 0.3s;
}

.user-btn:hover {
  background: #e8eaf6;
}

.user-avatar {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  object-fit: cover;
}

.user-name {
  font-size: 14px;
  color: #333;
  max-width: 80px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

/* 移动端菜单按钮 */
.mobile-menu-btn {
  display: none;
  width: 40px;
  height: 40px;
  border: none;
  background: transparent;
  border-radius: 8px;
  cursor: pointer;
  align-items: center;
  justify-content: center;
}

.mobile-menu-btn i {
  font-size: 24px;
  color: #666;
}

/* 移动端菜单 */
.mobile-menu {
  display: none;
  background: white;
  border-top: 1px solid #f0f0f0;
  padding: 16px 20px;
}

.mobile-nav-item {
  display: block;
  padding: 12px 16px;
  font-size: 16px;
  color: #333;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.3s;
}

.mobile-nav-item:hover {
  background: #f5f7fa;
  color: #667eea;
}

.mobile-nav-item.active {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
}

.mobile-login {
  margin-top: 16px;
  padding-top: 16px;
  border-top: 1px solid #f0f0f0;
}

.mobile-login-btn {
  width: 100%;
  padding: 12px;
  border: none;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  border-radius: 8px;
  font-size: 16px;
  cursor: pointer;
}

/* 动画 */
.slide-down-enter-active,
.slide-down-leave-active {
  transition: all 0.3s ease;
}

.slide-down-enter,
.slide-down-leave-to {
  opacity: 0;
  transform: translateY(-10px);
}

/* 主内容区 */
.main-content {
  flex: 1;
  min-height: calc(100vh - 64px - 200px);
}

/* 页脚 */
.footer {
  background: #1a1a2e;
  color: white;
  padding: 60px 20px 20px;
}

.footer-container {
  max-width: 1200px;
  margin: 0 auto;
}

.footer-content {
  display: grid;
  grid-template-columns: 2fr 1fr 1fr;
  gap: 40px;
  margin-bottom: 40px;
}

.footer-title {
  font-size: 18px;
  font-weight: 600;
  margin-bottom: 16px;
}

.footer-desc {
  color: #999;
  line-height: 1.6;
  margin-bottom: 20px;
}

.social-links {
  display: flex;
  gap: 12px;
}

.social-link {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background: rgba(255, 255, 255, 0.1);
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  text-decoration: none;
  transition: all 0.3s;
}

.social-link:hover {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.footer-links {
  list-style: none;
  padding: 0;
  margin: 0;
}

.footer-links li {
  margin-bottom: 12px;
}

.footer-links a {
  color: #999;
  text-decoration: none;
  cursor: pointer;
  transition: color 0.3s;
}

.footer-links a:hover {
  color: white;
}

.footer-bottom {
  text-align: center;
  padding-top: 20px;
  border-top: 1px solid rgba(255, 255, 255, 0.1);
  color: #666;
  font-size: 14px;
}

/* ================ 响应式布局 ================ */

/* iPad 平板 (768px - 1024px) */
@media screen and (max-width: 1024px) {
  .desktop-menu {
    display: none;
  }
  
  .mobile-menu-btn {
    display: flex;
  }
  
  .mobile-menu {
    display: block;
  }
  
  .footer-content {
    grid-template-columns: 1fr 1fr;
  }
}

/* 手机 (小于 768px) */
@media screen and (max-width: 768px) {
  .header-container {
    padding: 0 16px;
    height: 56px;
  }
  
  .logo-text {
    font-size: 18px;
  }
  
  .logo-img {
    width: 36px;
    height: 36px;
  }
  
  .search-btn {
    display: none;
  }
  
  .login-btn span,
  .user-name {
    display: none;
  }
  
  .footer {
    padding: 40px 16px 20px;
  }
  
  .footer-content {
    grid-template-columns: 1fr;
    gap: 32px;
  }
}

/* 图标字体 */
.iconfont {
  font-family: "iconfont";
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.icon-search::before { content: "\e7bc"; }
.icon-user::before { content: "\e7bd"; }
.icon-arrow-down::before { content: "\e7be"; }
.icon-menu::before { content: "\e7bf"; }
.icon-close::before { content: "\e7c0"; }
.icon-edit::before { content: "\e7c1"; }
.icon-manage::before { content: "\e7c2"; }
.icon-logout::before { content: "\e7c3"; }
.icon-github::before { content: "\e7c4"; }
.icon-twitter::before { content: "\e7c5"; }
.icon-email::before { content: "\e7c6"; }
</style>
