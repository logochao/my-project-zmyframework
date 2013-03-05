/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-3-1 上午10:47:48
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-3-1   wendellup     1.0       如果修改了;必填  
 */
package com.wendellup.app.dao.impl.movie;

import org.springframework.stereotype.Repository;
import org.wendellup.core.dao.DefaultDaoImpl;

import com.wendellup.app.dao.contract.movie.IMovieDao;
import com.wendellup.app.valueobject.entity.Movie;

@Repository("iMovieDao")
public class MovieDaoImpl extends DefaultDaoImpl<Movie> implements IMovieDao{

}
