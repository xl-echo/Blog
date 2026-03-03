<template>
  <header class="blog-header">
    <!-- 顶部导航栏 -->
    <nav class="top-nav">
      <div class="nav-container">
        <div class="nav-menu">
          <router-link to="/" class="nav-item">
            <i class="icon-home"></i> 首页
          </router-link>
          <router-link to="/archive" class="nav-item">
            <i class="icon-archive"></i> 档案馆
          </router-link>
          <router-link to="/friends" class="nav-item">
            <i class="icon-friends"></i> 好伙伴
          </router-link>
          <router-link to="/about" class="nav-item">
            <i class="icon-about"></i> 关于我
          </router-link>
        </div>
        <div class="nav-right">
          <a href="/rss" class="rss-link">
            <i class="icon-rss"></i> RSS订阅
          </a>
        </div>
      </div>
    </nav>

    <!-- 头部横幅 -->
    <div class="header-banner">
      <div class="banner-background"></div>
      <div class="banner-content">
        <h1 class="blog-title">糖菓·部落</h1>
        <p class="blog-subtitle">甜甜哒~</p>
      </div>
      <div class="banner-character"></div>
    </div>
  </header>
</template>

<script>
export default {
  name: 'Header',
  data() {
    return {
      // 组件数据
    }
  }
}
</script>

<style scoped>
/* 顶部导航栏 */
.top-nav {
  background: linear-gradient(135deg, #2c3e50 0%, #34495e 100%);
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3);
  position: sticky;
  top: 0;
  z-index: 1000;
}

.nav-container {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
  height: 60px;
}

.nav-menu {
  display: flex;
  gap: 30px;
}

.nav-item {
  color: #ecf0f1;
  text-decoration: none;
  font-size: 16px;
  font-weight: 500;
  display: flex;
  align-items: center;
  gap: 8px;
  transition: all 0.3s ease;
  padding: 8px 12px;
  border-radius: 4px;
}

.nav-item:hover {
  color: #f39c12;
  background: rgba(255, 255, 255, 0.1);
  transform: translateY(-2px);
}

.nav-right {
  display: flex;
  align-items: center;
}

.rss-link {
  color: #ecf0f1;
  text-decoration: none;
  font-size: 14px;
  display: flex;
  align-items: center;
  gap: 8px;
  transition: all 0.3s ease;
  padding: 6px 10px;
  border-radius: 4px;
}

.rss-link:hover {
  color: #e74c3c;
  background: rgba(255, 255, 255, 0.1);
}

/* 头部横幅 */
.header-banner {
  position: relative;
  height: 300px;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
}

.banner-background {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: url('https://images.unsplash.com/photo-1446776653964-20c1d3a81b06?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1920&q=80') center/cover;
  filter: brightness(0.7) saturate(1.2);
  animation: backgroundShift 20s ease-in-out infinite;
}

@keyframes backgroundShift {
  0%, 100% { transform: scale(1) translate(0, 0); }
  50% { transform: scale(1.1) translate(-5px, -5px); }
}

.banner-content {
  position: relative;
  z-index: 2;
  text-align: center;
  color: white;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
}

.blog-title {
  font-size: 48px;
  font-weight: 700;
  margin: 0 0 10px 0;
  font-family: 'Microsoft YaHei', sans-serif;
  animation: titleGlow 3s ease-in-out infinite alternate;
}

@keyframes titleGlow {
  from { text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5), 0 0 10px rgba(243, 156, 18, 0.3); }
  to { text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5), 0 0 20px rgba(243, 156, 18, 0.6); }
}

.blog-subtitle {
  font-size: 20px;
  margin: 0;
  opacity: 0.9;
  font-weight: 300;
}

.banner-character {
  position: absolute;
  right: 50px;
  bottom: 0;
  width: 200px;
  height: 300px;
  background: url('https://i.pinimg.com/736x/8c/98/99/8c98994518b575bfd8c949e91d20548b.jpg') bottom/cover;
  z-index: 1;
  animation: characterFloat 6s ease-in-out infinite;
}

@keyframes characterFloat {
  0%, 100% { transform: translateY(0px); }
  50% { transform: translateY(-10px); }
}

/* 图标样式 */
@font-face {
  font-family: 'BlogIcons';
  src: url('../assets/icons.ttf') format('truetype');
}

[class^="icon-"] {
  font-family: 'BlogIcons';
  speak: never;
  font-style: normal;
  font-weight: normal;
  font-variant: normal;
  text-transform: none;
  line-height: 1;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.icon-home::before { content: '🏠'; }
.icon-archive::before { content: '📚'; }
.icon-friends::before { content: '👫'; }
.icon-about::before { content: 'ℹ'; }
.icon-rss::before { content: '📰'; }

/* 响应式设计 */
@media (max-width: 768px) {
  .nav-container {
    flex-direction: column;
    height: auto;
    padding: 10px;
  }

  .nav-menu {
    margin-bottom: 10px;
    gap: 15px;
  }

  .blog-title {
    font-size: 32px;
  }

  .blog-subtitle {
    font-size: 16px;
  }

  .banner-character {
    width: 150px;
    height: 225px;
    right: 20px;
  }
}
</style>
