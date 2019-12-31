package rewriteweb.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class linkchecksExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public linkchecksExample() {
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLjUrlIsNull() {
            addCriterion("LJ_URL is null");
            return (Criteria) this;
        }

        public Criteria andLjUrlIsNotNull() {
            addCriterion("LJ_URL is not null");
            return (Criteria) this;
        }

        public Criteria andLjUrlEqualTo(String value) {
            addCriterion("LJ_URL =", value, "ljUrl");
            return (Criteria) this;
        }

        public Criteria andLjUrlNotEqualTo(String value) {
            addCriterion("LJ_URL <>", value, "ljUrl");
            return (Criteria) this;
        }

        public Criteria andLjUrlGreaterThan(String value) {
            addCriterion("LJ_URL >", value, "ljUrl");
            return (Criteria) this;
        }

        public Criteria andLjUrlGreaterThanOrEqualTo(String value) {
            addCriterion("LJ_URL >=", value, "ljUrl");
            return (Criteria) this;
        }

        public Criteria andLjUrlLessThan(String value) {
            addCriterion("LJ_URL <", value, "ljUrl");
            return (Criteria) this;
        }

        public Criteria andLjUrlLessThanOrEqualTo(String value) {
            addCriterion("LJ_URL <=", value, "ljUrl");
            return (Criteria) this;
        }

        public Criteria andLjUrlLike(String value) {
            addCriterion("LJ_URL like", value, "ljUrl");
            return (Criteria) this;
        }

        public Criteria andLjUrlNotLike(String value) {
            addCriterion("LJ_URL not like", value, "ljUrl");
            return (Criteria) this;
        }

        public Criteria andLjUrlIn(List<String> values) {
            addCriterion("LJ_URL in", values, "ljUrl");
            return (Criteria) this;
        }

        public Criteria andLjUrlNotIn(List<String> values) {
            addCriterion("LJ_URL not in", values, "ljUrl");
            return (Criteria) this;
        }

        public Criteria andLjUrlBetween(String value1, String value2) {
            addCriterion("LJ_URL between", value1, value2, "ljUrl");
            return (Criteria) this;
        }

        public Criteria andLjUrlNotBetween(String value1, String value2) {
            addCriterion("LJ_URL not between", value1, value2, "ljUrl");
            return (Criteria) this;
        }

        public Criteria andLjwbIsNull() {
            addCriterion("LJWB is null");
            return (Criteria) this;
        }

        public Criteria andLjwbIsNotNull() {
            addCriterion("LJWB is not null");
            return (Criteria) this;
        }

        public Criteria andLjwbEqualTo(String value) {
            addCriterion("LJWB =", value, "ljwb");
            return (Criteria) this;
        }

        public Criteria andLjwbNotEqualTo(String value) {
            addCriterion("LJWB <>", value, "ljwb");
            return (Criteria) this;
        }

        public Criteria andLjwbGreaterThan(String value) {
            addCriterion("LJWB >", value, "ljwb");
            return (Criteria) this;
        }

        public Criteria andLjwbGreaterThanOrEqualTo(String value) {
            addCriterion("LJWB >=", value, "ljwb");
            return (Criteria) this;
        }

        public Criteria andLjwbLessThan(String value) {
            addCriterion("LJWB <", value, "ljwb");
            return (Criteria) this;
        }

        public Criteria andLjwbLessThanOrEqualTo(String value) {
            addCriterion("LJWB <=", value, "ljwb");
            return (Criteria) this;
        }

        public Criteria andLjwbLike(String value) {
            addCriterion("LJWB like", value, "ljwb");
            return (Criteria) this;
        }

        public Criteria andLjwbNotLike(String value) {
            addCriterion("LJWB not like", value, "ljwb");
            return (Criteria) this;
        }

        public Criteria andLjwbIn(List<String> values) {
            addCriterion("LJWB in", values, "ljwb");
            return (Criteria) this;
        }

        public Criteria andLjwbNotIn(List<String> values) {
            addCriterion("LJWB not in", values, "ljwb");
            return (Criteria) this;
        }

        public Criteria andLjwbBetween(String value1, String value2) {
            addCriterion("LJWB between", value1, value2, "ljwb");
            return (Criteria) this;
        }

        public Criteria andLjwbNotBetween(String value1, String value2) {
            addCriterion("LJWB not between", value1, value2, "ljwb");
            return (Criteria) this;
        }

        public Criteria andZtxxIsNull() {
            addCriterion("ZTXX is null");
            return (Criteria) this;
        }

        public Criteria andZtxxIsNotNull() {
            addCriterion("ZTXX is not null");
            return (Criteria) this;
        }

        public Criteria andZtxxEqualTo(String value) {
            addCriterion("ZTXX =", value, "ztxx");
            return (Criteria) this;
        }

        public Criteria andZtxxNotEqualTo(String value) {
            addCriterion("ZTXX <>", value, "ztxx");
            return (Criteria) this;
        }

        public Criteria andZtxxGreaterThan(String value) {
            addCriterion("ZTXX >", value, "ztxx");
            return (Criteria) this;
        }

        public Criteria andZtxxGreaterThanOrEqualTo(String value) {
            addCriterion("ZTXX >=", value, "ztxx");
            return (Criteria) this;
        }

        public Criteria andZtxxLessThan(String value) {
            addCriterion("ZTXX <", value, "ztxx");
            return (Criteria) this;
        }

        public Criteria andZtxxLessThanOrEqualTo(String value) {
            addCriterion("ZTXX <=", value, "ztxx");
            return (Criteria) this;
        }

        public Criteria andZtxxLike(String value) {
            addCriterion("ZTXX like", value, "ztxx");
            return (Criteria) this;
        }

        public Criteria andZtxxNotLike(String value) {
            addCriterion("ZTXX not like", value, "ztxx");
            return (Criteria) this;
        }

        public Criteria andZtxxIn(List<String> values) {
            addCriterion("ZTXX in", values, "ztxx");
            return (Criteria) this;
        }

        public Criteria andZtxxNotIn(List<String> values) {
            addCriterion("ZTXX not in", values, "ztxx");
            return (Criteria) this;
        }

        public Criteria andZtxxBetween(String value1, String value2) {
            addCriterion("ZTXX between", value1, value2, "ztxx");
            return (Criteria) this;
        }

        public Criteria andZtxxNotBetween(String value1, String value2) {
            addCriterion("ZTXX not between", value1, value2, "ztxx");
            return (Criteria) this;
        }

        public Criteria andSfcsIsNull() {
            addCriterion("SFCS is null");
            return (Criteria) this;
        }

        public Criteria andSfcsIsNotNull() {
            addCriterion("SFCS is not null");
            return (Criteria) this;
        }

        public Criteria andSfcsEqualTo(String value) {
            addCriterion("SFCS =", value, "sfcs");
            return (Criteria) this;
        }

        public Criteria andSfcsNotEqualTo(String value) {
            addCriterion("SFCS <>", value, "sfcs");
            return (Criteria) this;
        }

        public Criteria andSfcsGreaterThan(String value) {
            addCriterion("SFCS >", value, "sfcs");
            return (Criteria) this;
        }

        public Criteria andSfcsGreaterThanOrEqualTo(String value) {
            addCriterion("SFCS >=", value, "sfcs");
            return (Criteria) this;
        }

        public Criteria andSfcsLessThan(String value) {
            addCriterion("SFCS <", value, "sfcs");
            return (Criteria) this;
        }

        public Criteria andSfcsLessThanOrEqualTo(String value) {
            addCriterion("SFCS <=", value, "sfcs");
            return (Criteria) this;
        }

        public Criteria andSfcsLike(String value) {
            addCriterion("SFCS like", value, "sfcs");
            return (Criteria) this;
        }

        public Criteria andSfcsNotLike(String value) {
            addCriterion("SFCS not like", value, "sfcs");
            return (Criteria) this;
        }

        public Criteria andSfcsIn(List<String> values) {
            addCriterion("SFCS in", values, "sfcs");
            return (Criteria) this;
        }

        public Criteria andSfcsNotIn(List<String> values) {
            addCriterion("SFCS not in", values, "sfcs");
            return (Criteria) this;
        }

        public Criteria andSfcsBetween(String value1, String value2) {
            addCriterion("SFCS between", value1, value2, "sfcs");
            return (Criteria) this;
        }

        public Criteria andSfcsNotBetween(String value1, String value2) {
            addCriterion("SFCS not between", value1, value2, "sfcs");
            return (Criteria) this;
        }

        public Criteria andZtIsNull() {
            addCriterion("ZT is null");
            return (Criteria) this;
        }

        public Criteria andZtIsNotNull() {
            addCriterion("ZT is not null");
            return (Criteria) this;
        }

        public Criteria andZtEqualTo(String value) {
            addCriterion("ZT =", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotEqualTo(String value) {
            addCriterion("ZT <>", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtGreaterThan(String value) {
            addCriterion("ZT >", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtGreaterThanOrEqualTo(String value) {
            addCriterion("ZT >=", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtLessThan(String value) {
            addCriterion("ZT <", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtLessThanOrEqualTo(String value) {
            addCriterion("ZT <=", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtLike(String value) {
            addCriterion("ZT like", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotLike(String value) {
            addCriterion("ZT not like", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtIn(List<String> values) {
            addCriterion("ZT in", values, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotIn(List<String> values) {
            addCriterion("ZT not in", values, "zt");
            return (Criteria) this;
        }

        public Criteria andZtBetween(String value1, String value2) {
            addCriterion("ZT between", value1, value2, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotBetween(String value1, String value2) {
            addCriterion("ZT not between", value1, value2, "zt");
            return (Criteria) this;
        }

        public Criteria andLjjcrqIsNull() {
            addCriterion("LJJCRQ is null");
            return (Criteria) this;
        }

        public Criteria andLjjcrqIsNotNull() {
            addCriterion("LJJCRQ is not null");
            return (Criteria) this;
        }

        public Criteria andLjjcrqEqualTo(String value) {
            addCriterion("LJJCRQ =", value, "ljjcrq");
            return (Criteria) this;
        }

        public Criteria andLjjcrqNotEqualTo(String value) {
            addCriterion("LJJCRQ <>", value, "ljjcrq");
            return (Criteria) this;
        }

        public Criteria andLjjcrqGreaterThan(String value) {
            addCriterion("LJJCRQ >", value, "ljjcrq");
            return (Criteria) this;
        }

        public Criteria andLjjcrqGreaterThanOrEqualTo(String value) {
            addCriterion("LJJCRQ >=", value, "ljjcrq");
            return (Criteria) this;
        }

        public Criteria andLjjcrqLessThan(String value) {
            addCriterion("LJJCRQ <", value, "ljjcrq");
            return (Criteria) this;
        }

        public Criteria andLjjcrqLessThanOrEqualTo(String value) {
            addCriterion("LJJCRQ <=", value, "ljjcrq");
            return (Criteria) this;
        }

        public Criteria andLjjcrqLike(String value) {
            addCriterion("LJJCRQ like", value, "ljjcrq");
            return (Criteria) this;
        }

        public Criteria andLjjcrqNotLike(String value) {
            addCriterion("LJJCRQ not like", value, "ljjcrq");
            return (Criteria) this;
        }

        public Criteria andLjjcrqIn(List<String> values) {
            addCriterion("LJJCRQ in", values, "ljjcrq");
            return (Criteria) this;
        }

        public Criteria andLjjcrqNotIn(List<String> values) {
            addCriterion("LJJCRQ not in", values, "ljjcrq");
            return (Criteria) this;
        }

        public Criteria andLjjcrqBetween(String value1, String value2) {
            addCriterion("LJJCRQ between", value1, value2, "ljjcrq");
            return (Criteria) this;
        }

        public Criteria andLjjcrqNotBetween(String value1, String value2) {
            addCriterion("LJJCRQ not between", value1, value2, "ljjcrq");
            return (Criteria) this;
        }

        public Criteria andLjszwyIdIsNull() {
            addCriterion("LJSZWY_ID is null");
            return (Criteria) this;
        }

        public Criteria andLjszwyIdIsNotNull() {
            addCriterion("LJSZWY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLjszwyIdEqualTo(String value) {
            addCriterion("LJSZWY_ID =", value, "ljszwyId");
            return (Criteria) this;
        }

        public Criteria andLjszwyIdNotEqualTo(String value) {
            addCriterion("LJSZWY_ID <>", value, "ljszwyId");
            return (Criteria) this;
        }

        public Criteria andLjszwyIdGreaterThan(String value) {
            addCriterion("LJSZWY_ID >", value, "ljszwyId");
            return (Criteria) this;
        }

        public Criteria andLjszwyIdGreaterThanOrEqualTo(String value) {
            addCriterion("LJSZWY_ID >=", value, "ljszwyId");
            return (Criteria) this;
        }

        public Criteria andLjszwyIdLessThan(String value) {
            addCriterion("LJSZWY_ID <", value, "ljszwyId");
            return (Criteria) this;
        }

        public Criteria andLjszwyIdLessThanOrEqualTo(String value) {
            addCriterion("LJSZWY_ID <=", value, "ljszwyId");
            return (Criteria) this;
        }

        public Criteria andLjszwyIdLike(String value) {
            addCriterion("LJSZWY_ID like", value, "ljszwyId");
            return (Criteria) this;
        }

        public Criteria andLjszwyIdNotLike(String value) {
            addCriterion("LJSZWY_ID not like", value, "ljszwyId");
            return (Criteria) this;
        }

        public Criteria andLjszwyIdIn(List<String> values) {
            addCriterion("LJSZWY_ID in", values, "ljszwyId");
            return (Criteria) this;
        }

        public Criteria andLjszwyIdNotIn(List<String> values) {
            addCriterion("LJSZWY_ID not in", values, "ljszwyId");
            return (Criteria) this;
        }

        public Criteria andLjszwyIdBetween(String value1, String value2) {
            addCriterion("LJSZWY_ID between", value1, value2, "ljszwyId");
            return (Criteria) this;
        }

        public Criteria andLjszwyIdNotBetween(String value1, String value2) {
            addCriterion("LJSZWY_ID not between", value1, value2, "ljszwyId");
            return (Criteria) this;
        }

        public Criteria andSsrwIdIsNull() {
            addCriterion("SSRW_ID is null");
            return (Criteria) this;
        }

        public Criteria andSsrwIdIsNotNull() {
            addCriterion("SSRW_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSsrwIdEqualTo(String value) {
            addCriterion("SSRW_ID =", value, "ssrwId");
            return (Criteria) this;
        }

        public Criteria andSsrwIdNotEqualTo(String value) {
            addCriterion("SSRW_ID <>", value, "ssrwId");
            return (Criteria) this;
        }

        public Criteria andSsrwIdGreaterThan(String value) {
            addCriterion("SSRW_ID >", value, "ssrwId");
            return (Criteria) this;
        }

        public Criteria andSsrwIdGreaterThanOrEqualTo(String value) {
            addCriterion("SSRW_ID >=", value, "ssrwId");
            return (Criteria) this;
        }

        public Criteria andSsrwIdLessThan(String value) {
            addCriterion("SSRW_ID <", value, "ssrwId");
            return (Criteria) this;
        }

        public Criteria andSsrwIdLessThanOrEqualTo(String value) {
            addCriterion("SSRW_ID <=", value, "ssrwId");
            return (Criteria) this;
        }

        public Criteria andSsrwIdLike(String value) {
            addCriterion("SSRW_ID like", value, "ssrwId");
            return (Criteria) this;
        }

        public Criteria andSsrwIdNotLike(String value) {
            addCriterion("SSRW_ID not like", value, "ssrwId");
            return (Criteria) this;
        }

        public Criteria andSsrwIdIn(List<String> values) {
            addCriterion("SSRW_ID in", values, "ssrwId");
            return (Criteria) this;
        }

        public Criteria andSsrwIdNotIn(List<String> values) {
            addCriterion("SSRW_ID not in", values, "ssrwId");
            return (Criteria) this;
        }

        public Criteria andSsrwIdBetween(String value1, String value2) {
            addCriterion("SSRW_ID between", value1, value2, "ssrwId");
            return (Criteria) this;
        }

        public Criteria andSsrwIdNotBetween(String value1, String value2) {
            addCriterion("SSRW_ID not between", value1, value2, "ssrwId");
            return (Criteria) this;
        }

        public Criteria andLjlxIsNull() {
            addCriterion("LJLX is null");
            return (Criteria) this;
        }

        public Criteria andLjlxIsNotNull() {
            addCriterion("LJLX is not null");
            return (Criteria) this;
        }

        public Criteria andLjlxEqualTo(BigDecimal value) {
            addCriterion("LJLX =", value, "ljlx");
            return (Criteria) this;
        }

        public Criteria andLjlxNotEqualTo(BigDecimal value) {
            addCriterion("LJLX <>", value, "ljlx");
            return (Criteria) this;
        }

        public Criteria andLjlxGreaterThan(BigDecimal value) {
            addCriterion("LJLX >", value, "ljlx");
            return (Criteria) this;
        }

        public Criteria andLjlxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LJLX >=", value, "ljlx");
            return (Criteria) this;
        }

        public Criteria andLjlxLessThan(BigDecimal value) {
            addCriterion("LJLX <", value, "ljlx");
            return (Criteria) this;
        }

        public Criteria andLjlxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LJLX <=", value, "ljlx");
            return (Criteria) this;
        }

        public Criteria andLjlxIn(List<BigDecimal> values) {
            addCriterion("LJLX in", values, "ljlx");
            return (Criteria) this;
        }

        public Criteria andLjlxNotIn(List<BigDecimal> values) {
            addCriterion("LJLX not in", values, "ljlx");
            return (Criteria) this;
        }

        public Criteria andLjlxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LJLX between", value1, value2, "ljlx");
            return (Criteria) this;
        }

        public Criteria andLjlxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LJLX not between", value1, value2, "ljlx");
            return (Criteria) this;
        }
    }

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