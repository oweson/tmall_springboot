package com.how2java.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;

@ToString
@Table(name = "card")
@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Setter
@Getter
@Document(indexName = "tmall_springboot", type = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String code;
}
