package com.tcps.origin.appcore.file;

import com.tcps.origin.appcore.repository.RepositoryCurd;
import org.springframework.stereotype.Repository;

/**
 * @author Gaowy
 * @date 2018/9/2
 * @mail 317326646@qq.com
 */
@Repository
public class FileRepository extends RepositoryCurd {

    @Override
    public Class<?> whichEntityClassNeedCURD() {
        return FileBean.class;
    }
}
