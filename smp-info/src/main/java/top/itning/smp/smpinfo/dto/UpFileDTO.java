package top.itning.smp.smpinfo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author itning
 */
@Data
@AllArgsConstructor
public class UpFileDTO implements Serializable {
    private Long now;
    private Long total;
    private List<String> error;
}
