package zxf.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Zxf
 * @date 2019/5/7  21:57
 */
@Data
public class Device {
    private int dev_id;
    private String dev_identity;  //编号 例如C001
    private String dev_name;
    private String dev_specification;  //规格型号 例如S7-1250KVA/10KV
    private String dev_type;
    private String dev_manufacturer;   //生产厂商
    private int dev_value;
    private String dev_department;     //所在部门
    private String dev_installaddr;
    private String dev_usingstatus;
    private String dev_operater;
    private Date dev_buytime ;
    private Date dev_maintaintime;   //维修时间
    private String dev_identification;  //设备标识 例如 一般设备、大型设备、小型设备
    private int    dev_departId=-1;
    public Device(){}

    public Device(String dev_identity, String dev_name, String dev_specification, String dev_type, String dev_manufacturer, String dev_installaddr, String dev_usingstatus, String dev_operater, Date dev_buytime, String dev_identification) {
        this.dev_identity = dev_identity;
        this.dev_name = dev_name;
        this.dev_specification = dev_specification;
        this.dev_type = dev_type;
        this.dev_manufacturer = dev_manufacturer;
        this.dev_installaddr = dev_installaddr;
        this.dev_usingstatus = dev_usingstatus;
        this.dev_operater = dev_operater;
        this.dev_buytime = dev_buytime;
        this.dev_identification = dev_identification;
    }

    public Device(String dev_identity, String dev_name, String dev_type, String dev_manufacturer, String dev_usingstatus, String dev_identification) {
        this.dev_identity = dev_identity;
        this.dev_name = dev_name;
        this.dev_type = dev_type;
        this.dev_manufacturer = dev_manufacturer;
        this.dev_usingstatus = dev_usingstatus;
        this.dev_identification = dev_identification;
    }

    /*
* 14个参数*/
    public Device(String dev_identity, String dev_name, String dev_specification, String dev_type, String dev_manufacturer, int dev_value, String dev_department, String dev_installaddr, String dev_usingstatus, String dev_operater, Date dev_buytime, Date dev_maintaintime, String dev_identification) {
        this.dev_identity = dev_identity;
        this.dev_name = dev_name;
        this.dev_specification = dev_specification;
        this.dev_type = dev_type;
        this.dev_manufacturer = dev_manufacturer;
        this.dev_value = dev_value;
        this.dev_department = dev_department;
        this.dev_installaddr = dev_installaddr;
        this.dev_usingstatus = dev_usingstatus;
        this.dev_operater = dev_operater;
        this.dev_buytime = dev_buytime;
        this.dev_maintaintime = dev_maintaintime;
        this.dev_identification = dev_identification;
    }
}
