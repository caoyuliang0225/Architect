package com.cloud.architect.data.jpa.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Cao Yuliang on 2019-07-08.
 *
 * @MappedSuperclass
 * 1.注解使用在父类上面，是用来标识父类的
 * 2.标识的类表示其不能映射到数据库表，因为其不是一个完整的实体类，但是它所拥有的属性能够隐射在其子类对用的数据库表中
 * 3.标识得类不能再有@Entity或@Table注解
 *
 * @EntityListeners
 * 对实体属性变化的跟踪，它提供了保存前，保存后，更新前，更新后，删除前，删除后等状态，就像是拦截器一样，你可以在拦截方法里重写你的个性化逻辑。
 * spring data 已经写好了 AuditingEntityListener 直接使用即可。
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractModel extends AbstractPersistable<Long> implements Serializable {

    private static final long serialVersionUID = 5172817586248878563L;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createdDate;

    @Column(nullable = false)
    @LastModifiedDate
    private Date lastModifiedDate;

    @Getter
    @Setter
    @Version
    @Column(nullable = false)
    private Long version;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
