package whatseating.backend.domain.detail.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import whatseating.backend.global.entity.BaseTimeEntity;
import io.hypersistence.utils.hibernate.type.json.JsonType;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "detail")
public class Detail extends BaseTimeEntity {
    @Id
    private String id;

    @Column(nullable = false)
    private String basicInfo;

    @Type(JsonType.class)
    @Column(columnDefinition = "JSON")
    private String comment;

    @Type(JsonType.class)
    @Column(columnDefinition = "JSON")
    private String menuInfo;

    @Type(JsonType.class)
    @Column(columnDefinition = "JSON")
    private String photo;

    @Builder
    public Detail(String id, String basicInfo, String comment, String menuInfo, String photo) {
        this.id = id;
        this.basicInfo = basicInfo;
        this.comment = comment;
        this.menuInfo = menuInfo;
        this.photo = photo;
    }
}
