package dao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Description:
 * Created by 李晓 ON 2017/11/22.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader: 李晓
 */
@Entity
public class model {
    @Id
    private int id;

    @Generated(hash = 916243921)
    public model(int id) {
        this.id = id;
    }

    @Generated(hash = 1861450556)
    public model() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
