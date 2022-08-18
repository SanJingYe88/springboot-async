package it.com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 浏览记录
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class History {
    private String title;
    private Date date;
}
