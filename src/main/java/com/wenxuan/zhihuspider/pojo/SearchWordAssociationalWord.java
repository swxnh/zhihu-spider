package com.wenxuan.zhihuspider.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @TableName t_search_word_associational_word
 */
public class SearchWordAssociationalWord implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 搜索词
     */
    private String searchWord;

    /**
     * 联想词id
     */
    private Long associationalWordId;

    /**
     * 分数
     */
    private BigDecimal score;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 搜索词
     */
    public String getSearchWord() {
        return searchWord;
    }

    /**
     * 搜索词
     */
    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    /**
     * 联想词id
     */
    public Long getAssociationalWordId() {
        return associationalWordId;
    }

    /**
     * 联想词id
     */
    public void setAssociationalWordId(Long associationalWordId) {
        this.associationalWordId = associationalWordId;
    }

    /**
     * 分数
     */
    public BigDecimal getScore() {
        return score;
    }

    /**
     * 分数
     */
    public void setScore(BigDecimal score) {
        this.score = score;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SearchWordAssociationalWord other = (SearchWordAssociationalWord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSearchWord() == null ? other.getSearchWord() == null : this.getSearchWord().equals(other.getSearchWord()))
            && (this.getAssociationalWordId() == null ? other.getAssociationalWordId() == null : this.getAssociationalWordId().equals(other.getAssociationalWordId()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSearchWord() == null) ? 0 : getSearchWord().hashCode());
        result = prime * result + ((getAssociationalWordId() == null) ? 0 : getAssociationalWordId().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", searchWord=").append(searchWord);
        sb.append(", associationalWordId=").append(associationalWordId);
        sb.append(", score=").append(score);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}