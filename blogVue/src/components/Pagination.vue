<template>
  <div class="pagination">
    <button 
      class="pagination-btn" 
      :disabled="currentPage === 1"
      @click="goToPage(1)"
    >
      <i class="icon-first"></i>
    </button>
    
    <button 
      class="pagination-btn" 
      :disabled="currentPage === 1"
      @click="goToPage(currentPage - 1)"
    >
      <i class="icon-prev"></i>
    </button>
    
    <button 
      v-for="page in visiblePages" 
      :key="page"
      class="pagination-page"
      :class="{ active: page === currentPage, ellipsis: page === '...' }"
      @click="goToPage(page)"
      :disabled="page === '...'"
    >
      {{ page }}
    </button>
    
    <button 
      class="pagination-btn" 
      :disabled="currentPage === totalPages"
      @click="goToPage(currentPage + 1)"
    >
      <i class="icon-next"></i>
    </button>
    
    <button 
      class="pagination-btn" 
      :disabled="currentPage === totalPages"
      @click="goToPage(totalPages)"
    >
      <i class="icon-last"></i>
    </button>
    
    <div class="pagination-info">
      第 {{ currentPage }} 页，共 {{ totalPages }} 页
    </div>
  </div>
</template>

<script>
export default {
  name: 'Pagination',
  props: {
    currentPage: {
      type: Number,
      required: true
    },
    totalPages: {
      type: Number,
      required: true
    },
    showPages: {
      type: Number,
      default: 5
    }
  },
  computed: {
    visiblePages() {
      const pages = [];
      const half = Math.floor(this.showPages / 2);
      
      if (this.totalPages <= this.showPages) {
        for (let i = 1; i <= this.totalPages; i++) {
          pages.push(i);
        }
      } else {
        if (this.currentPage <= half + 1) {
          for (let i = 1; i <= this.showPages - 1; i++) {
            pages.push(i);
          }
          pages.push('...');
          pages.push(this.totalPages);
        } else if (this.currentPage >= this.totalPages - half) {
          pages.push(1);
          pages.push('...');
          for (let i = this.totalPages - this.showPages + 2; i <= this.totalPages; i++) {
            pages.push(i);
          }
        } else {
          pages.push(1);
          pages.push('...');
          for (let i = this.currentPage - half + 1; i <= this.currentPage + half - 1; i++) {
            pages.push(i);
          }
          pages.push('...');
          pages.push(this.totalPages);
        }
      }
      
      return pages;
    }
  },
  methods: {
    goToPage(page) {
      if (page === '...' || page === this.currentPage) return;
      
      if (page < 1) page = 1;
      if (page > this.totalPages) page = this.totalPages;
      
      this.$emit('page-change', page);
    }
  }
}
</script>

<style scoped>
.pagination {
  display: flex;
  align-items: center;
  gap: 10px;
  justify-content: center;
  padding: 20px 0;
}

.pagination-btn {
  width: 45px;
  height: 45px;
  border: 2px solid #eee;
  background: white;
  color: #666;
  border-radius: 50%;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.3s ease;
  font-size: 16px;
}

.pagination-btn:hover:not(:disabled) {
  border-color: #f39c12;
  color: #f39c12;
  transform: translateY(-2px);
}

.pagination-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.pagination-page {
  min-width: 45px;
  height: 45px;
  border: 2px solid #eee;
  background: white;
  color: #666;
  border-radius: 8px;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.3s ease;
  font-size: 16px;
  font-weight: 500;
}

.pagination-page:hover:not(.active):not(.ellipsis) {
  border-color: #f39c12;
  color: #f39c12;
  transform: translateY(-2px);
}

.pagination-page.active {
  border-color: #f39c12;
  background: #f39c12;
  color: white;
  font-weight: 700;
}

.pagination-page.ellipsis {
  border: none;
  cursor: default;
  color: #999;
}

.pagination-page.ellipsis:hover {
  transform: none;
}

.pagination-info {
  margin-left: 20px;
  font-size: 14px;
  color: #666;
  font-weight: 300;
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

.icon-first::before { content: '⏮'; }
.icon-prev::before { content: '◀'; }
.icon-next::before { content: '▶'; }
.icon-last::before { content: '⏭'; }

/* 响应式设计 */
@media (max-width: 768px) {
  .pagination {
    flex-wrap: wrap;
    gap: 5px;
  }
  
  .pagination-btn {
    width: 40px;
    height: 40px;
    font-size: 14px;
  }
  
  .pagination-page {
    min-width: 40px;
    height: 40px;
    font-size: 14px;
  }
  
  .pagination-info {
    margin-left: 0;
    width: 100%;
    text-align: center;
    margin-top: 10px;
  }
}
</style>
