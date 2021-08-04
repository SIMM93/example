package com.smartbr.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BannerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    private Boolean forUpdate;

    public BannerExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

    public void setForUpdate(Boolean forUpdate) {
        this.forUpdate = forUpdate;
    }

    public Boolean getForUpdate() {
        return forUpdate;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("banner.id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("banner.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("banner.id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("banner.id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("banner.id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("banner.id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("banner.id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("banner.id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("banner.id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("banner.id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("banner.id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("banner.id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("banner.create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("banner.create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("banner.create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("banner.create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("banner.create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("banner.create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("banner.create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("banner.create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("banner.create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("banner.create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("banner.create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("banner.create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andHerfIsNull() {
            addCriterion("banner.herf is null");
            return (Criteria) this;
        }

        public Criteria andHerfIsNotNull() {
            addCriterion("banner.herf is not null");
            return (Criteria) this;
        }

        public Criteria andHerfEqualTo(String value) {
            addCriterion("banner.herf =", value, "herf");
            return (Criteria) this;
        }

        public Criteria andHerfNotEqualTo(String value) {
            addCriterion("banner.herf <>", value, "herf");
            return (Criteria) this;
        }

        public Criteria andHerfGreaterThan(String value) {
            addCriterion("banner.herf >", value, "herf");
            return (Criteria) this;
        }

        public Criteria andHerfGreaterThanOrEqualTo(String value) {
            addCriterion("banner.herf >=", value, "herf");
            return (Criteria) this;
        }

        public Criteria andHerfLessThan(String value) {
            addCriterion("banner.herf <", value, "herf");
            return (Criteria) this;
        }

        public Criteria andHerfLessThanOrEqualTo(String value) {
            addCriterion("banner.herf <=", value, "herf");
            return (Criteria) this;
        }

        public Criteria andHerfLike(String value) {
            addCriterion("banner.herf like", value, "herf");
            return (Criteria) this;
        }

        public Criteria andHerfNotLike(String value) {
            addCriterion("banner.herf not like", value, "herf");
            return (Criteria) this;
        }

        public Criteria andHerfIn(List<String> values) {
            addCriterion("banner.herf in", values, "herf");
            return (Criteria) this;
        }

        public Criteria andHerfNotIn(List<String> values) {
            addCriterion("banner.herf not in", values, "herf");
            return (Criteria) this;
        }

        public Criteria andHerfBetween(String value1, String value2) {
            addCriterion("banner.herf between", value1, value2, "herf");
            return (Criteria) this;
        }

        public Criteria andHerfNotBetween(String value1, String value2) {
            addCriterion("banner.herf not between", value1, value2, "herf");
            return (Criteria) this;
        }

        public Criteria andHerfNameIsNull() {
            addCriterion("banner.herf_name is null");
            return (Criteria) this;
        }

        public Criteria andHerfNameIsNotNull() {
            addCriterion("banner.herf_name is not null");
            return (Criteria) this;
        }

        public Criteria andHerfNameEqualTo(String value) {
            addCriterion("banner.herf_name =", value, "herfName");
            return (Criteria) this;
        }

        public Criteria andHerfNameNotEqualTo(String value) {
            addCriterion("banner.herf_name <>", value, "herfName");
            return (Criteria) this;
        }

        public Criteria andHerfNameGreaterThan(String value) {
            addCriterion("banner.herf_name >", value, "herfName");
            return (Criteria) this;
        }

        public Criteria andHerfNameGreaterThanOrEqualTo(String value) {
            addCriterion("banner.herf_name >=", value, "herfName");
            return (Criteria) this;
        }

        public Criteria andHerfNameLessThan(String value) {
            addCriterion("banner.herf_name <", value, "herfName");
            return (Criteria) this;
        }

        public Criteria andHerfNameLessThanOrEqualTo(String value) {
            addCriterion("banner.herf_name <=", value, "herfName");
            return (Criteria) this;
        }

        public Criteria andHerfNameLike(String value) {
            addCriterion("banner.herf_name like", value, "herfName");
            return (Criteria) this;
        }

        public Criteria andHerfNameNotLike(String value) {
            addCriterion("banner.herf_name not like", value, "herfName");
            return (Criteria) this;
        }

        public Criteria andHerfNameIn(List<String> values) {
            addCriterion("banner.herf_name in", values, "herfName");
            return (Criteria) this;
        }

        public Criteria andHerfNameNotIn(List<String> values) {
            addCriterion("banner.herf_name not in", values, "herfName");
            return (Criteria) this;
        }

        public Criteria andHerfNameBetween(String value1, String value2) {
            addCriterion("banner.herf_name between", value1, value2, "herfName");
            return (Criteria) this;
        }

        public Criteria andHerfNameNotBetween(String value1, String value2) {
            addCriterion("banner.herf_name not between", value1, value2, "herfName");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
