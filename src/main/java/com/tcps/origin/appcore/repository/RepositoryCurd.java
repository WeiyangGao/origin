package com.tcps.origin.appcore.repository;

import com.tcps.origin.appcore.annotations.Table;
import com.tcps.origin.config.constant.RepositoryConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.util.StringUtils;

import java.util.List;

public abstract class RepositoryCurd {
    private Class<?> cls;
    private String tableName;

    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public RepositoryCurd() {
        this.cls = whichEntityClassNeedCURD();
        annotationHandler();
    }

    public abstract Class<?> whichEntityClassNeedCURD();

    private void annotationHandler() {
        Table tableAnnotation = cls.getAnnotation(Table.class);
        if (tableAnnotation == null)
            throw new RuntimeException(cls + RepositoryConstant.NO_TABLE_ANNOTATION);
        String tableName = tableAnnotation.name();
        if (StringUtils.isEmpty(tableName))
            throw new RuntimeException(cls + RepositoryConstant.NO_TABLE_NAME_VALUE);
        this.tableName = tableName;
    }

    public RepositoryPageInfo<?> queryByPageForOracle(final String yourQuerySQL, RepositoryPageInfo<?> pageInfo) {
        pageInfo = countPageInfo(pageInfo, yourQuerySQL);
        int pageStartIndex = pageInfo.getPageStartIndex();
        int pageEndIndex = pageInfo.getPageEndIndex();
        String excuteSQL = "SELECT * FROM (SELECT tt.*, ROWNUM AS rowno FROM " + "(" + yourQuerySQL + ") tt "
                + " WHERE ROWNUM <= " + pageEndIndex + ") table_alias" + " WHERE table_alias.rowno > " + pageStartIndex;
        List<?> resultList = excuteSQL(excuteSQL, new BeanPropertySqlParameterSource(pageInfo.getEntity()));
        pageInfo.setQueryResultList(resultList);
        return pageInfo;
    }

    private RepositoryPageInfo<?> countPageInfo(final RepositoryPageInfo<?> pageInfo, final String sql) {
        int totalCount = namedParameterJdbcTemplate.queryForObject("SELECT count(1) " + "FROM " + "(" + sql + ")",
                new BeanPropertySqlParameterSource(pageInfo.getEntity()), Integer.class);
        pageInfo.setTotalCount(totalCount);
        return pageInfo;
    }

    private List<?> excuteSQL(String sql, final SqlParameterSource sqlParameterSource) {
        return this.namedParameterJdbcTemplate.query(sql, sqlParameterSource, new BeanPropertyRowMapper<>(cls));
    }

    public boolean simpleSave(Object object) {
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(object);
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource());
        simpleJdbcInsert.setTableName(tableName);
        return simpleJdbcInsert.execute(sqlParameterSource) > 0;
    }

    public boolean simpleDeleteById(Long id) {
        return jdbcTemplate.update("delete from " + tableName + " where id = ?", new Object[]{id}) > 0;
    }

    public boolean simpleUpdateById(Object object) {
        BeanPropertySqlParameterSource bean = new BeanPropertySqlParameterSource(object);
        String[] parameterNames = bean.getParameterNames();
        StringBuilder sql = new StringBuilder("update ").append(tableName).append(" set ");
        for (String param : parameterNames) {
            if (!"class".equals(param) && !StringUtils.isEmpty(bean.getValue(param))) {
                sql.append(entityField2TableColum(param)).append(" =:").append(param).append(",");
            }
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" where id =:id");
        return this.namedParameterJdbcTemplate.update(sql.toString(), bean) > 0;
    }

    public List<?> findEntityById(Long id) {
        return jdbcTemplate.query("select * from " + tableName + " where id =?", new Object[]{id},
                new BeanPropertyRowMapper<>(cls));
    }

    private String entityField2TableColum(final String fieldName) {
        char[] paramArray = fieldName.toCharArray();
        StringBuilder param = new StringBuilder();
        for (int i = 0; i < fieldName.length(); i++) {
            char p = paramArray[i];
            if (Character.isUpperCase(p)) {
                param.append("_").append(p);
            } else {
                param.append(p);
            }
        }
        return param.toString();
    }
}
