<template>
  <div class="home-container">
    <!-- 顶部大图 Banner -->
    <div class="hero-banner">
      <div class="banner-overlay"></div>
      <img src="https://images.unsplash.com/photo-1519681393784-d120267933ba?w=1920&h=400&fit=crop" alt="banner" class="banner-image"/>
      <div class="banner-content">
        <h1 class="site-title">糖菓部落</h1>
        <p class="site-desc">分享技术、记录生活、探索二次元</p>
      </div>
    </div>

    <!-- 主内容区 -->
    <div class="main-content">
      <!-- 左侧文章列表 -->
      <div class="content-left">
        <div class="section-header">
          <h2 class="section-title">
            <i class="iconfont icon-article"></i>
            最新文章
          </h2>
        </div>

        <!-- 文章列表 -->
        <div v-if="articles.length > 0" class="article-list">
          <div class="article-card" v-for="article in articles" :key="article.id" @click="goToDetail(article.id)">
            <div class="article-image">
              <img :src="article.cover || defaultCover" :alt="article.title"/>
              <span class="article-category">{{ getTypeText(article.ifOriginal) }}</span>
            </div>
            <div class="article-info">
              <h3 class="article-title">{{ article.title }}</h3>
              <p class="article-summary">{{ article.summary || article.content ? article.content.substring(0, 100) + '...' : '暂无描述' }}</p>
              <div class="article-meta">
                <span class="meta-item">
                  <i class="iconfont icon-calendar"></i>
                  <span class="meta-label">发布时间：</span>{{ formatDate(article.createTime) }}
                </span>
                <span class="meta-item">
                  <i class="iconfont icon-eye"></i>
                  <span class="meta-label">阅读量：</span>{{ article.views || 0 }}
                </span>
                <span class="meta-item">
                  <i class="iconfont icon-like"></i>
                  <span class="meta-label">点赞：</span>{{ article.likes || 0 }}
                </span>
              </div>
            </div>
          </div>
        </div>

        <!-- 空状态 -->
        <div v-else class="empty-state">
          <div class="empty-icon">
            <i class="iconfont icon-empty"></i>
          </div>
          <p class="empty-text">暂时没有文章</p>
          <p class="empty-subtext">敬请期待更多精彩内容...</p>
        </div>

        <!-- 分页 -->
        <div v-if="total > 0" class="pagination-wrapper">
          <Page 
            :total="total" 
            :page-size="pageSize"
            :current="currentPage"
            @on-change="pageChange"
            show-elevator
            show-total
          />
        </div>
      </div>

      <!-- 右侧侧边栏 -->
      <div class="content-right">
        <!-- 个人简介卡片 -->
        <div class="sidebar-card profile-card">
          <div class="profile-avatar">
            <img src="https://images.unsplash.com/photo-1517841905240-472988babdf9?w=200&h=200&fit=crop" alt="avatar"/>
          </div>
          <h3 class="profile-name">糖菓</h3>
          <p class="profile-bio">热爱技术，喜欢二次元，记录生活点滴</p>
          <div class="profile-stats">
            <div class="stat-item">
              <span class="stat-num">{{ total }}</span>
              <span class="stat-label">文章</span>
            </div>
            <div class="stat-item">
              <span class="stat-num">0</span>
              <span class="stat-label">分类</span>
            </div>
            <div class="stat-item">
              <span class="stat-num">0</span>
              <span class="stat-label">标签</span>
            </div>
          </div>
        </div>

        <!-- 热门标签 -->
        <div class="sidebar-card">
          <h3 class="card-title">
            <i class="iconfont icon-tag"></i>
            热门标签
          </h3>
          <div class="tag-cloud">
            <span class="tag-item" v-for="tag in hotTags" :key="tag">{{ tag }}</span>
          </div>
        </div>

        <!-- 最新评论 -->
        <div class="sidebar-card">
          <h3 class="card-title">
            <i class="iconfont icon-comment"></i>
            最新评论
          </h3>
          <div class="comment-list">
            <div class="comment-item" v-for="i in 3" :key="i">
              <div class="comment-user">访客{{ i }}</div>
              <div class="comment-content">期待更多精彩文章！</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'home',
  data() {
    return {
      defaultCover: 'https://images.unsplash.com/photo-1499750310107-5fef28a66643?w=400&h=300&fit=crop',
      articles: [],
      total: 0,
      pageSize: 10,
      currentPage: 1,
      hotTags: ['Vue', 'Java', 'Spring', 'MySQL', 'Redis', 'Docker', 'Linux', '前端']
    }
  },
  created() {
    console.log('home.vue created 钩子被调用')
    this.loadArticles()
  },
  mounted() {
    console.log('home.vue mounted 钩子被调用')
  },
  methods: {
    loadArticles() {
      // 从后端 API 获取文章列表和总数
      const pageNum = (this.currentPage - 1) * this.pageSize
      console.log('开始加载文章列表，pageNum:', pageNum, 'pageSize:', this.pageSize)

      // 获取文章列表
      this.axios.get('/articleController/getList', {
        params: {
          pageNum: pageNum,
          pageSize: this.pageSize
        }
      }).then(res => {
        console.log('获取文章列表响应:', res)
        if (res.data.status === 200) {
          this.articles = res.data.data || []
          console.log('文章列表加载成功，数量:', this.articles.length)
        } else {
          console.log('文章列表加载失败，status:', res.data.status)
          this.articles = []
        }
      }).catch(err => {
        console.error('获取文章列表失败:', err)
        this.articles = []
      })

      // 获取文章总数
      this.axios.get('/articleController/getCount').then(res => {
        console.log('获取文章总数响应:', res)
        if (res.data.status === 200) {
          this.total = res.data.data || 0
          console.log('文章总数:', this.total)
        }
      }).catch(err => {
        console.error('获取文章总数失败:', err)
      })
    },
    pageChange(page) {
      this.currentPage = page
      this.loadArticles()
    },
    goToDetail(id) {
      localStorage.setItem('articleId', id)
      this.$router.push('/pageDetail')
    },
    getTypeText(ifOriginal) {
      const typeMap = { 0: '原创', 1: '转载', 2: '翻译' }
      return typeMap[ifOriginal] || '其他'
    },
    formatDate(date) {
      if (!date) return ''
      const d = new Date(date)
      return `${d.getFullYear()}-${String(d.getMonth() + 1).padStart(2, '0')}-${String(d.getDate()).padStart(2, '0')}`
    }
  }
}
</script>

<style scoped>
/* 基础样式 */
.home-container {
  min-height: 100vh;
  background: #f5f7fa;
}

/* Banner 区域 */
.hero-banner {
  position: relative;
  width: 100%;
  height: 400px;
  overflow: hidden;
}

.banner-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.banner-overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: linear-gradient(135deg, rgba(102, 126, 234, 0.6) 0%, rgba(118, 75, 162, 0.6) 100%);
  z-index: 1;
}

.banner-content {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
  color: white;
  z-index: 2;
}

.site-title {
  font-size: 48px;
  font-weight: 700;
  margin-bottom: 16px;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
}

.site-desc {
  font-size: 18px;
  opacity: 0.9;
}

/* 主内容区 */
.main-content {
  max-width: 1200px;
  margin: -60px auto 0;
  padding: 0 20px 40px;
  display: flex;
  gap: 24px;
  position: relative;
  z-index: 10;
}

.content-left {
  flex: 1;
  min-width: 0;
  width: 100%;
}

.content-right {
  width: 320px;
  flex-shrink: 0;
}

/* 文章列表容器 */
.article-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

/* 区域标题 */
.section-header {
  background: white;
  padding: 20px 24px;
  border-radius: 12px;
  margin-bottom: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
}

.section-title {
  font-size: 20px;
  font-weight: 600;
  color: #333;
  margin: 0;
  display: flex;
  align-items: center;
  gap: 8px;
}

.section-title i {
  color: #667eea;
}

/* 文章卡片 - 垂直布局，一行一篇 */
.article-card {
  background: white;
  border-radius: 12px;
  overflow: hidden;
  margin-bottom: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  transition: all 0.3s ease;
  cursor: pointer;
  display: flex;
  flex-direction: column;
}

.article-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.12);
}

.article-image {
  width: 100%;
  height: 200px;
  position: relative;
  flex-shrink: 0;
}

.article-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.article-category {
  position: absolute;
  top: 12px;
  left: 12px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  padding: 4px 12px;
  border-radius: 20px;
  font-size: 12px;
}

.article-info {
  flex: 1;
  padding: 20px 24px;
  display: flex;
  flex-direction: column;
}

.article-title {
  font-size: 20px;
  font-weight: 600;
  color: #333;
  margin: 0 0 12px 0;
  line-height: 1.4;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.article-summary {
  font-size: 14px;
  color: #666;
  line-height: 1.8;
  margin: 0 0 16px 0;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}

.article-meta {
  display: flex;
  gap: 24px;
  color: #666;
  font-size: 13px;
}

.meta-item {
  display: flex;
  align-items: center;
  gap: 4px;
}

.meta-item i {
  font-size: 14px;
  color: #999;
}

.meta-label {
  color: #999;
}

/* 空状态 */
.empty-state {
  background: white;
  border-radius: 12px;
  padding: 80px 40px;
  text-align: center;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
}

.empty-icon {
  font-size: 80px;
  color: #ddd;
  margin-bottom: 20px;
}

.empty-text {
  font-size: 18px;
  color: #666;
  margin-bottom: 8px;
}

.empty-subtext {
  font-size: 14px;
  color: #999;
}

/* 分页 */
.pagination-wrapper {
  display: flex;
  justify-content: center;
  padding: 20px 0;
}

/* 侧边栏卡片 */
.sidebar-card {
  background: white;
  border-radius: 12px;
  padding: 24px;
  margin-bottom: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
}

.card-title {
  font-size: 16px;
  font-weight: 600;
  color: #333;
  margin: 0 0 16px 0;
  display: flex;
  align-items: center;
  gap: 8px;
}

.card-title i {
  color: #667eea;
}

/* 个人简介卡片 */
.profile-card {
  text-align: center;
}

.profile-avatar {
  width: 100px;
  height: 100px;
  margin: 0 auto 16px;
  border-radius: 50%;
  overflow: hidden;
  border: 4px solid #f0f0f0;
}

.profile-avatar img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.profile-name {
  font-size: 20px;
  font-weight: 600;
  color: #333;
  margin: 0 0 8px 0;
}

.profile-bio {
  font-size: 14px;
  color: #666;
  margin: 0 0 20px 0;
  line-height: 1.5;
}

.profile-stats {
  display: flex;
  justify-content: center;
  gap: 24px;
  padding-top: 20px;
  border-top: 1px solid #f0f0f0;
}

.stat-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 4px;
}

.stat-num {
  font-size: 20px;
  font-weight: 600;
  color: #667eea;
}

.stat-label {
  font-size: 12px;
  color: #999;
}

/* 标签云 */
.tag-cloud {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}

.tag-item {
  background: #f5f7fa;
  color: #666;
  padding: 6px 14px;
  border-radius: 20px;
  font-size: 13px;
  cursor: pointer;
  transition: all 0.3s;
}

.tag-item:hover {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
}

/* 评论列表 */
.comment-list {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.comment-item {
  padding-bottom: 16px;
  border-bottom: 1px solid #f0f0f0;
}

.comment-item:last-child {
  padding-bottom: 0;
  border-bottom: none;
}

.comment-user {
  font-size: 14px;
  font-weight: 600;
  color: #667eea;
  margin-bottom: 4px;
}

.comment-content {
  font-size: 13px;
  color: #666;
  line-height: 1.5;
}

/* ================ 响应式布局 ================ */

/* iPad 平板 (768px - 1024px) */
@media screen and (max-width: 1024px) {
  .main-content {
    flex-direction: column;
  }
  
  .content-right {
    width: 100%;
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 20px;
  }
  
  .sidebar-card {
    margin-bottom: 0;
  }
  
  .article-image {
    width: 240px;
    height: 160px;
  }
}

/* 手机 (小于 768px) */
@media screen and (max-width: 768px) {
  .hero-banner {
    height: 250px;
  }
  
  .site-title {
    font-size: 32px;
  }
  
  .site-desc {
    font-size: 14px;
  }
  
  .main-content {
    margin-top: -30px;
    padding: 0 12px 30px;
    flex-direction: column;
  }
  
  .content-right {
    width: 100%;
    display: flex;
    flex-direction: column;
    gap: 16px;
  }
  
  .article-card {
    flex-direction: column;
  }
  
  .article-image {
    width: 100%;
    height: 200px;
  }
  
  .article-info {
    padding: 16px;
  }
  
  .article-title {
    font-size: 16px;
  }
  
  .article-meta {
    flex-wrap: wrap;
    gap: 12px;
  }
  
  .profile-stats {
    gap: 16px;
  }
}

/* 小屏手机 (小于 480px) */
@media screen and (max-width: 480px) {
  .hero-banner {
    height: 200px;
  }
  
  .site-title {
    font-size: 24px;
  }
  
  .section-header {
    padding: 16px;
  }
  
  .section-title {
    font-size: 18px;
  }
  
  .sidebar-card {
    padding: 16px;
  }
  
  .profile-avatar {
    width: 80px;
    height: 80px;
  }
  
  .tag-cloud {
    gap: 6px;
  }
  
  .tag-item {
    padding: 4px 10px;
    font-size: 12px;
  }
}

/* 图标字体 */
.iconfont {
  font-family: "iconfont";
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.icon-article::before { content: "\e7b5"; }
.icon-tag::before { content: "\e7b6"; }
.icon-comment::before { content: "\e7b7"; }
.icon-calendar::before { content: "\e7b8"; }
.icon-eye::before { content: "\e7b9"; }
.icon-like::before { content: "\e7ba"; }
.icon-empty::before { content: "\e7bb"; }
</style>
