package com.wilson404.demo.test.dto;

public class DemoDto {
    private String name;
    private String nativeName;

    public DemoDto() {
    }

    public DemoDto(String name, String nativeName) {
        this.name = name;
        this.nativeName = nativeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    @Override
    public String toString() {
        return "DemoDto{" +
                "name='" + name + '\'' +
                ", nativeName='" + nativeName + '\'' +
                '}';
    }
}
