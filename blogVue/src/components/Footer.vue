<template>
  <footer class="blog-footer">
    <div class="footer-container">
      <div class="footer-content">
        <div class="footer-section footer-about">
          <h3 class="section-title">糖菓·部落</h3>
          <p class="section-description">
            一个分享技术、生活和二次元的个人博客
            记录成长的点点滴滴，分享有趣的事物
          </p>
          <div class="social-links">
            <a href="#" class="social-link" title="GitHub">
              <i class="icon-github"></i>
            </a>
            <a href="#" class="social-link" title="Twitter">
              <i class="icon-twitter"></i>
            </a>
            <a href="#" class="social-link" title="QQ">
              <i class="icon-qq"></i>
            </a>
            <a href="#" class="social-link" title="邮箱">
              <i class="icon-email"></i>
            </a>
          </div>
        </div>
        
        <div class="footer-section footer-links">
          <h3 class="section-title">快速链接</h3>
          <ul class="link-list">
            <li class="link-item">
              <router-link to="/" class="link">
                <i class="icon-home"></i>
                首页
              </router-link>
            </li>
            <li class="link-item">
              <router-link to="/archive" class="link">
                <i class="icon-archive"></i>
                档案馆
              </router-link>
            </li>
            <li class="link-item">
              <router-link to="/friends" class="link">
                <i class="icon-friends"></i>
                好伙伴
              </router-link>
            </li>
            <li class="link-item">
              <router-link to="/about" class="link">
                <i class="icon-about"></i>
                关于我
              </router-link>
            </li>
          </ul>
        </div>
        
        <div class="footer-section footer-categories">
          <h3 class="section-title">热门分类</h3>
          <ul class="category-list">
            <li class="category-item">
              <router-link to="/category/1" class="category-link">
                <i class="icon-category"></i>
                全新体验
                <span class="category-count">11</span>
              </router-link>
            </li>
            <li class="category-item">
              <router-link to="/category/2" class="category-link">
                <i class="icon-category"></i>
                开发日志
                <span class="category-count">16</span>
              </router-link>
            </li>
            <li class="category-item">
              <router-link to="/category/3" class="category-link">
                <i class="icon-category"></i>
                教程文档
                <span class="category-count">16</span>
              </router-link>
            </li>
            <li class="category-item">
              <router-link to="/category/4" class="category-link">
                <i class="icon-category"></i>
                纷繁杂谈
                <span class="category-count">2</span>
              </router-link>
            </li>
          </ul>
        </div>
        
        <div class="footer-section footer-subscribe">
          <h3 class="section-title">订阅博客</h3>
          <p class="section-description">
            订阅我们的博客，获取最新的文章更新
            我们不会发送垃圾邮件
          </p>
          <form class="subscribe-form">
            <input 
              type="email" 
              class="subscribe-input" 
              placeholder="输入您的邮箱"
              v-model="email"
            />
            <button type="submit" class="subscribe-btn" @click.prevent="subscribe">
              <i class="icon-subscribe"></i>
              订阅
            </button>
          </form>
        </div>
      </div>
      
      <div class="footer-bottom">
        <div class="bottom-content">
          <p class="copyright">
            © 2024 糖菓·部落. All rights reserved.
            <br>
            Powered by <a href="#" class="powered-link">Vue.js</a> & <a href="#" class="powered-link">Spring Boot</a>
          </p>
          <p class="icp">
            <a href="#" class="icp-link">
              粤ICP备12345678号
            </a>
          </p>
        </div>
      </div>
    </div>
    
    <div class="footer-decoration">
      <div class="decoration-character"></div>
      <div class="decoration-stars">
        <div class="star" v-for="i in 20" :key="i"></div>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: 'Footer',
  data() {
    return {
      email: ''
    }
  },
  methods: {
    subscribe() {
      if (!this.email) {
        this.$message.warning('请输入邮箱地址');
        return;
      }
      
      // 这里可以添加订阅逻辑
      this.$message.success('订阅成功！感谢您的关注');
      this.email = '';
    }
  }
}
</script>

<style scoped>
.blog-footer {
  background: linear-gradient(to bottom, #2c3e50 0%, #1a252f 100%);
  color: white;
  position: relative;
  overflow: hidden;
  margin-top: 60px;
}

.footer-container {
  position: relative;
  z-index: 2;
}

.footer-content {
  max-width: 1400px;
  margin: 0 auto;
  padding: 60px 20px;
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 40px;
}

.section-title {
  font-size: 18px;
  font-weight: 600;
  margin: 0 0 20px 0;
  color: #f39c12;
}

.section-description {
  font-size: 14px;
  line-height: 1.8;
  color: #bdc3c7;
  margin: 0 0 20px 0;
}

/* 关于我们部分 */
.footer-about {
  text-align: center;
}

.social-links {
  display: flex;
  justify-content: center;
  gap: 15px;
}

.social-link {
  width: 45px;
  height: 45px;
  background: rgba(255, 255, 255, 0.1);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  text-decoration: none;
  transition: all 0.3s ease;
  font-size: 20px;
}

.social-link:hover {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.3);
}

/* 快速链接部分 */
.link-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.link-item {
  margin-bottom: 15px;
}

.link {
  display: flex;
  align-items: center;
  gap: 10px;
  color: #bdc3c7;
  text-decoration: none;
  font-size: 14px;
  transition: all 0.3s ease;
  padding: 8px 0;
}

.link:hover {
  color: #f39c12;
  padding-left: 10px;
}

/* 热门分类部分 */
.category-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.category-item {
  margin-bottom: 15px;
}

.category-link {
  display: flex;
  align-items: center;
  justify-content: space-between;
  color: #bdc3c7;
  text-decoration: none;
  font-size: 14px;
  transition: all 0.3s ease;
  padding: 8px 0;
}

.category-link:hover {
  color: #f39c12;
  padding-left: 10px;
}

.category-count {
  background: rgba(243, 156, 18, 0.2);
  color: #f39c12;
  padding: 2px 8px;
  border-radius: 10px;
  font-size: 12px;
  font-weight: 600;
}

/* 订阅部分 */
.subscribe-form {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.subscribe-input {
  padding: 12px 15px;
  border: 2px solid rgba(255, 255, 255, 0.2);
  background: rgba(255, 255, 255, 0.1);
  color: white;
  border-radius: 8px;
  font-size: 14px;
  transition: all 0.3s ease;
}

.subscribe-input:focus {
  outline: none;
  border-color: #f39c12;
  background: rgba(255, 255, 255, 0.15);
}

.subscribe-input::placeholder {
  color: #95a5a6;
}

.subscribe-btn {
  padding: 12px 20px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
}

.subscribe-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 10px 20px rgba(102, 126, 234, 0.3);
}

/* 底部信息 */
.footer-bottom {
  background: rgba(0, 0, 0, 0.3);
  padding: 30px 20px;
  border-top: 1px solid rgba(255, 255, 255, 0.1);
}

.bottom-content {
  max-width: 1400px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  gap: 20px;
}

.copyright {
  margin: 0;
  font-size: 13px;
  color: #95a5a6;
  line-height: 1.6;
}

.powered-link {
  color: #f39c12;
  text-decoration: none;
  transition: all 0.3s ease;
}

.powered-link:hover {
  color: #e67e22;
  text-decoration: underline;
}

.icp-link {
  color: #95a5a6;
  text-decoration: none;
  font-size: 13px;
  transition: all 0.3s ease;
}

.icp-link:hover {
  color: #f39c12;
}

/* 装饰元素 */
.footer-decoration {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 1;
  overflow: hidden;
}

.decoration-character {
  position: absolute;
  left: 50px;
  bottom: 0;
  width: 150px;
  height: 250px;
  background: url('https://i.pinimg.com/736x/8c/98/99/8c98994518b575bfd8c949e91d20548b.jpg') bottom/cover;
  opacity: 0.1;
  animation: characterFloat 8s ease-in-out infinite;
}

.decoration-stars {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
}

.star {
  position: absolute;
  width: 2px;
  height: 2px;
  background: white;
  border-radius: 50%;
  opacity: 0.8;
  animation: starTwinkle 3s ease-in-out infinite;
}

.star:nth-child(1) { top: 20%; left: 10%; animation-delay: 0s; }
.star:nth-child(2) { top: 40%; left: 20%; animation-delay: 0.2s; }
.star:nth-child(3) { top: 60%; left: 30%; animation-delay: 0.4s; }
.star:nth-child(4) { top: 80%; left: 40%; animation-delay: 0.6s; }
.star:nth-child(5) { top: 10%; left: 50%; animation-delay: 0.8s; }
.star:nth-child(6) { top: 30%; left: 60%; animation-delay: 1s; }
.star:nth-child(7) { top: 50%; left: 70%; animation-delay: 1.2s; }
.star:nth-child(8) { top: 70%; left: 80%; animation-delay: 1.4s; }
.star:nth-child(9) { top: 90%; left: 90%; animation-delay: 1.6s; }
.star:nth-child(10) { top: 25%; left: 75%; animation-delay: 1.8s; }
.star:nth-child(11) { top: 45%; left: 25%; animation-delay: 2s; }
.star:nth-child(12) { top: 65%; left: 55%; animation-delay: 2.2s; }
.star:nth-child(13) { top: 85%; left: 15%; animation-delay: 2.4s; }
.star:nth-child(14) { top: 15%; left: 85%; animation-delay: 2.6s; }
.star:nth-child(15) { top: 35%; left: 45%; animation-delay: 2.8s; }
.star:nth-child(16) { top: 55%; left: 95%; animation-delay: 3s; }
.star:nth-child(17) { top: 75%; left: 35%; animation-delay: 3.2s; }
.star:nth-child(18) { top: 95%; left: 65%; animation-delay: 3.4s; }
.star:nth-child(19) { top: 5%; left: 45%; animation-delay: 3.6s; }
.star:nth-child(20) { top: 20%; left: 90%; animation-delay: 3.8s; }

/* 动画 */
@keyframes characterFloat {
  0%, 100% { transform: translateY(0px); }
  50% { transform: translateY(-10px); }
}

@keyframes starTwinkle {
  0%, 100% { opacity: 0.8; transform: scale(1); }
  50% { opacity: 0.3; transform: scale(1.5); }
}

/* 图标样式 */
[class^="icon-"] {
  font-family: 'BlogIcons';
  speak: never;
  font-style: normal;
  font-weight: normal;
  font-variant: normal;
  text-transform: none;
  line-height: 1;
}

.icon-github::before { content: '🐙'; }
.icon-twitter::before { content: '🐦'; }
.icon-qq::before { content: '🐧'; }
.icon-email::before { content: '📧'; }
.icon-home::before { content: '🏠'; }
.icon-archive::before { content: '📚'; }
.icon-friends::before { content: '👫'; }
.icon-about::before { content: 'ℹ'; }
.icon-category::before { content: '📁'; }
.icon-subscribe::before { content: '📰'; }

/* 响应式设计 */
@media (max-width: 768px) {
  .footer-content {
    padding: 40px 20px;
    grid-template-columns: 1fr;
    gap: 40px;
  }
  
  .footer-about {
    text-align: left;
  }
  
  .social-links {
    justify-content: flex-start;
  }
  
  .bottom-content {
    flex-direction: column;
    text-align: center;
  }
  
  .decoration-character {
    display: none;
  }
}
</style>
