package fr.jukien.intellij.plugins.util;

import com.intellij.database.model.DataType;

/**
 * Created on 24/04/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
public class Field {
    private String name;
    private Boolean isPrimary;
    private DataType SQLType;
    private String javaType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    public DataType getSQLType() {
        return SQLType;
    }

    public void setSQLType(DataType SQLType) {
        this.SQLType = SQLType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }
}