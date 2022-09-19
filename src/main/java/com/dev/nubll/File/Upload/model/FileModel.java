package com.dev.nubll.File.Upload.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name ="files")
@Getter
@Setter
@NoArgsConstructor
public class FileModel {
    public FileModel(String name,String type,byte[]data){
        this.data = data;
        this.type = type;
        this.name = name;
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String type;
    @Lob
    private byte[] data;
}
