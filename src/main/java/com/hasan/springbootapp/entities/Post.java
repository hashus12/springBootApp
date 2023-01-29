package com.hasan.springbootapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="post")
@Data
public class Post {

    @Id
    Long id;

//    Bire çok ilişki yani birden çok post bir usera bağlı olabilir.
//    fetchtype.Lazy ->User objesini db den hemen çekme. yani post objesini çektiğimde ilgili userı bana getirmene gerek yok diyoruz.Bunlar bağlantılı
//    nesneler olduğu için postu çektiğimde aynı şekilde içinde user objesi gelirdi eğer fetch.Type.Eager yazsaydım. Lazy yazdığımızdan
//    gelmicek
//    join column -> db deki user_id colonu ile user tablosuna objesine bağlandığımızı söylüyoruz.
//    OnDelete bir user silindiğinde sen gel bunun bütün postlarınıda sil diyoruz.
//    Serialization kısmında bize problem çıkarmasın diye bu alanı ignore et diyoruz. Çünkü aslında bu alanı fetchde etmicez(Lazy) bu alanla işimiz yok diyoruz.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    User user;

//    tabloyu create ederken ilk böyle yazık
//    Long userId;

    String title;
    @Lob
    @Column(columnDefinition = "text")
    String text;
}
