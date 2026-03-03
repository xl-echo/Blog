<template>
  <div class="sidebar-container">
    <!-- 左侧侧边栏 - 角色立绘 -->
    <aside class="left-sidebar">
      <div class="character-container">
        <div class="character-image"></div>
        <div class="music-player" v-if="showMusicPlayer">
          <div class="music-info">
            <p class="music-title">{{ currentMusic.title }}</p>
            <p class="music-artist">{{ currentMusic.artist }}</p>
          </div>
          <div class="music-controls">
            <button class="control-btn" @click="playPause">
              <i class="icon-play"></i>
            </button>
            <button class="control-btn" @click="nextSong">
              <i class="icon-next"></i>
            </button>
          </div>
        </div>
      </div>
    </aside>

    <!-- 右侧侧边栏 - 博主信息和统计 -->
    <aside class="right-sidebar">
      <div class="sidebar-card">
        <div class="profile-section">
          <div class="profile-avatar"></div>
          <div class="profile-info">
            <h3 class="profile-name">糖菓部落</h3>
            <p class="profile-signature">
              We are kids chasing our dreams<br>
              Never drown the flame of hope
            </p>
          </div>
        </div>

        <div class="stats-section">
          <div class="stat-item">
            <div class="stat-number">{{ stats.articles }}</div>
            <div class="stat-label">文章</div>
          </div>
          <div class="stat-item">
            <div class="stat-number">{{ stats.categories }}</div>
            <div class="stat-label">分类</div>
          </div>
          <div class="stat-item">
            <div class="stat-number">{{ stats.tags }}</div>
            <div class="stat-label">标签</div>
          </div>
        </div>
      </div>

      <div class="sidebar-card">
        <h3 class="card-title">分类列表</h3>
        <div class="category-list">
          <div class="category-item" v-for="category in categories" :key="category.id">
            <router-link :to="`/category/${category.id}`" class="category-link">
              <i class="icon-category"></i>
              <span class="category-name">{{ category.name }}</span>
              <span class="category-count">{{ category.count }}</span>
            </router-link>
          </div>
        </div>
      </div>

      <div class="sidebar-card">
        <h3 class="card-title">标签聚合</h3>
        <div class="tag-cloud">
          <router-link 
            :to="`/tag/${tag.id}`" 
            class="tag-item" 
            v-for="tag in tags" 
            :key="tag.id"
            :style="{ fontSize: tag.fontSize + 'px', color: tag.color }"
          >
            {{ tag.name }}
          </router-link>
        </div>
      </div>
    </aside>
  </div>
</template>

<script>
export default {
  name: 'Sidebar',
  data() {
    return {
      showMusicPlayer: true,
      currentMusic: {
        title: '二次元背景音乐',
        artist: '未知艺术家'
      },
      stats: {
        articles: 50,
        categories: 5,
        tags: 112
      },
      categories: [
        { id: 1, name: '全新体验', count: 11 },
        { id: 2, name: '开发日志', count: 16 },
        { id: 3, name: '教程文档', count: 16 },
        { id: 4, name: '纷繁杂谈', count: 2 },
        { id: 5, name: '运维手记', count: 2 }
      ],
      tags: [
        { id: 1, name: 'API', fontSize: 14, color: '#e74c3c' },
        { id: 2, name: 'Hexo', fontSize: 16, color: '#3498db' },
        { id: 3, name: 'Kratos', fontSize: 18, color: '#2ecc71' },
        { id: 4, name: 'GPS', fontSize: 15, color: '#f39c12' },
        { id: 5, name: 'NTP', fontSize: 17, color: '#9b59b6' },
        { id: 6, name: '硬件', fontSize: 13, color: '#1abc9c' },
        { id: 7, name: 'PVE', fontSize: 19, color: '#e67e22' },
        { id: 8, name: 'GitLab', fontSize: 16, color: '#34495e' },
        { id: 9, name: 'Ceph', fontSize: 14, color: '#27ae60' },
        { id: 10, name: 'HA', fontSize: 15, color: '#d35400' }
      ]
    }
  },
  methods: {
    playPause() {
      // 播放/暂停逻辑
      console.log('播放/暂停音乐');
    },
    nextSong() {
      // 下一首逻辑
      console.log('下一首音乐');
    }
  }
}
</script>

<style scoped>
/* 左侧侧边栏 */
.left-sidebar {
  position: fixed;
  left: 0;
  top: 0;
  bottom: 0;
  width: 220px;
  background: linear-gradient(to bottom, #f8f9fa 0%, #e9ecef 100%);
  z-index: 999;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
}

.character-container {
  position: relative;
  width: 100%;
  height: 100%;
  display: flex;
  align-items: flex-end;
  justify-content: center;
  padding-bottom: 20px;
}

.character-image {
  width: 180px;
  height: 400px;
  background: url('https://i.pinimg.com/736x/8c/98/99/8c98994518b575bfd8c949e91d20548b.jpg') bottom/cover;
  border-radius: 10px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
  animation: characterSway 8s ease-in-out infinite;
}

@keyframes characterSway {
  0%, 100% { transform: rotate(0deg) translateY(0px); }
  25% { transform: rotate(1deg) translateY(-2px); }
  75% { transform: rotate(-1deg) translateY(2px); }
}

.music-player {
  position: absolute;
  bottom: 20px;
  left: 20px;
  right: 20px;
  background: rgba(255, 255, 255, 0.95);
  border-radius: 10px;
  padding: 15px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
  backdrop-filter: blur(10px);
}

.music-info {
  margin-bottom: 10px;
  text-align: center;
}

.music-title {
  margin: 0 0 5px 0;
  font-size: 14px;
  font-weight: 600;
  color: #333;
}

.music-artist {
  margin: 0;
  font-size: 12px;
  color: #666;
}

.music-controls {
  display: flex;
  justify-content: center;
  gap: 10px;
}

.control-btn {
  width: 40px;
  height: 40px;
  border: none;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  border-radius: 50%;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.3s ease;
  box-shadow: 0 3px 10px rgba(102, 126, 234, 0.3);
}

.control-btn:hover {
  transform: scale(1.1);
  box-shadow: 0 5px 15px rgba(102, 126, 234, 0.5);
}

/* 右侧侧边栏 */
.right-sidebar {
  position: fixed;
  right: 0;
  top: 360px;
  bottom: 20px;
  width: 300px;
  overflow-y: auto;
  z-index: 998;
  padding: 0 20px;
}

.sidebar-card {
  background: white;
  border-radius: 15px;
  padding: 20px;
  margin-bottom: 20px;
  box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
}

.sidebar-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.15);
}

.card-title {
  font-size: 18px;
  font-weight: 600;
  margin: 0 0 20px 0;
  color: #333;
  border-bottom: 2px solid #f39c12;
  padding-bottom: 10px;
}

/* 个人资料部分 */
.profile-section {
  text-align: center;
  margin-bottom: 20px;
}

.profile-avatar {
  width: 80px;
  height: 80px;
  margin: 0 auto 15px;
  background: url('https://i.pinimg.com/736x/8c/98/99/8c98994518b575bfd8c949e91d20548b.jpg') center/cover;
  border-radius: 50%;
  border: 3px solid #f39c12;
  box-shadow: 0 5px 15px rgba(243, 156, 18, 0.3);
}

.profile-name {
  font-size: 20px;
  font-weight: 600;
  margin: 0 0 10px 0;
  color: #333;
}

.profile-signature {
  font-size: 13px;
  line-height: 1.6;
  color: #666;
  margin: 0;
}

/* 统计部分 */
.stats-section {
  display: flex;
  justify-content: space-around;
  padding: 15px 0;
  border-top: 1px solid #eee;
}

.stat-item {
  text-align: center;
}

.stat-number {
  font-size: 24px;
  font-weight: 700;
  color: #f39c12;
  margin-bottom: 5px;
}

.stat-label {
  font-size: 12px;
  color: #666;
  text-transform: uppercase;
}

/* 分类列表 */
.category-list {
  space-y: 10px;
}

.category-item {
  margin-bottom: 10px;
}

.category-link {
  display: flex;
  align-items: center;
  padding: 10px;
  border-radius: 8px;
  text-decoration: none;
  color: #333;
  transition: all 0.3s ease;
  background: #f8f9fa;
}

.category-link:hover {
  background: #f39c12;
  color: white;
  transform: translateX(5px);
}

.category-name {
  flex: 1;
  margin: 0 10px;
  font-size: 14px;
}

.category-count {
  font-size: 12px;
  background: rgba(243, 156, 18, 0.2);
  color: #f39c12;
  padding: 2px 6px;
  border-radius: 10px;
  font-weight: 600;
}

.category-link:hover .category-count {
  background: rgba(255, 255, 255, 0.2);
  color: white;
}

/* 标签云 */
.tag-cloud {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}

.tag-item {
  text-decoration: none;
  padding: 6px 12px;
  border-radius: 20px;
  background: #f8f9fa;
  font-size: 14px;
  font-weight: 500;
  transition: all 0.3s ease;
  border: 1px solid #eee;
}

.tag-item:hover {
  transform: translateY(-3px) scale(1.05);
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
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

.icon-play::before { content: '▶'; }
.icon-next::before { content: '⏭'; }
.icon-category::before { content: '📁'; }

/* 滚动条样式 */
.right-sidebar::-webkit-scrollbar {
  width: 6px;
}

.right-sidebar::-webkit-scrollbar-track {
  background: #f1f1f1;
  border-radius: 3px;
}

.right-sidebar::-webkit-scrollbar-thumb {
  background: #f39c12;
  border-radius: 3px;
}

.right-sidebar::-webkit-scrollbar-thumb:hover {
  background: #e67e22;
}

/* 响应式设计 */
@media (max-width: 1200px) {
  .left-sidebar {
    display: none;
  }
  
  .right-sidebar {
    position: static;
    width: auto;
    margin-top: 20px;
  }
}
</style>
