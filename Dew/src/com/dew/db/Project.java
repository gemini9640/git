package com.dew.db;

import java.util.Date;

public class Project {
    private Integer id;

    private String project;

    private String title;

    private String caption;

    private String content;

    private Date createTime;
    
    private String imgPath;

    public Project(Integer id, String project, String title, String caption, String content, Date createTime, String imgPath) {
        this.id = id;
        this.project = project;
        this.title = title;
        this.caption = caption;
        this.content = content;
        this.createTime = createTime;
        this.imgPath = imgPath;
    }

    public Project() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project == null ? null : project.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption == null ? null : caption.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", project=" + project + ", title="
				+ title + ", caption=" + caption + ", content=" + content
				+ ", createTime=" + createTime + ", imgPath=" + imgPath + "]";
	}
	
	
}