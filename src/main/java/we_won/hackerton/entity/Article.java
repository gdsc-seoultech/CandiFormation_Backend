package we_won.hackerton.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "article")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseEntity{

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String thumnail;

    @Column(nullable = false)
    private String news_agency;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private long like_num = 0;

    //heloo
    @Column(nullable = false)
    private long comment_num = 0;

    @Column(nullable = false)
    private String images;

    @Column(nullable = false,name = "date_time")
    private String date_time;

}
