package rewriteweb.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class warnsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public warnsExample() {
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

        public Criteria andXxwbIsNull() {
            addCriterion("XXWB is null");
            return (Criteria) this;
        }

        public Criteria andXxwbIsNotNull() {
            addCriterion("XXWB is not null");
            return (Criteria) this;
        }

        public Criteria andXxwbEqualTo(String value) {
            addCriterion("XXWB =", value, "xxwb");
            return (Criteria) this;
        }

        public Criteria andXxwbNotEqualTo(String value) {
            addCriterion("XXWB <>", value, "xxwb");
            return (Criteria) this;
        }

        public Criteria andXxwbGreaterThan(String value) {
            addCriterion("XXWB >", value, "xxwb");
            return (Criteria) this;
        }

        public Criteria andXxwbGreaterThanOrEqualTo(String value) {
            addCriterion("XXWB >=", value, "xxwb");
            return (Criteria) this;
        }

        public Criteria andXxwbLessThan(String value) {
            addCriterion("XXWB <", value, "xxwb");
            return (Criteria) this;
        }

        public Criteria andXxwbLessThanOrEqualTo(String value) {
            addCriterion("XXWB <=", value, "xxwb");
            return (Criteria) this;
        }

        public Criteria andXxwbLike(String value) {
            addCriterion("XXWB like", value, "xxwb");
            return (Criteria) this;
        }

        public Criteria andXxwbNotLike(String value) {
            addCriterion("XXWB not like", value, "xxwb");
            return (Criteria) this;
        }

        public Criteria andXxwbIn(List<String> values) {
            addCriterion("XXWB in", values, "xxwb");
            return (Criteria) this;
        }

        public Criteria andXxwbNotIn(List<String> values) {
            addCriterion("XXWB not in", values, "xxwb");
            return (Criteria) this;
        }

        public Criteria andXxwbBetween(String value1, String value2) {
            addCriterion("XXWB between", value1, value2, "xxwb");
            return (Criteria) this;
        }

        public Criteria andXxwbNotBetween(String value1, String value2) {
            addCriterion("XXWB not between", value1, value2, "xxwb");
            return (Criteria) this;
        }

        public Criteria andXxlxIsNull() {
            addCriterion("XXLX is null");
            return (Criteria) this;
        }

        public Criteria andXxlxIsNotNull() {
            addCriterion("XXLX is not null");
            return (Criteria) this;
        }

        public Criteria andXxlxEqualTo(BigDecimal value) {
            addCriterion("XXLX =", value, "xxlx");
            return (Criteria) this;
        }

        public Criteria andXxlxNotEqualTo(BigDecimal value) {
            addCriterion("XXLX <>", value, "xxlx");
            return (Criteria) this;
        }

        public Criteria andXxlxGreaterThan(BigDecimal value) {
            addCriterion("XXLX >", value, "xxlx");
            return (Criteria) this;
        }

        public Criteria andXxlxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("XXLX >=", value, "xxlx");
            return (Criteria) this;
        }

        public Criteria andXxlxLessThan(BigDecimal value) {
            addCriterion("XXLX <", value, "xxlx");
            return (Criteria) this;
        }

        public Criteria andXxlxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("XXLX <=", value, "xxlx");
            return (Criteria) this;
        }

        public Criteria andXxlxIn(List<BigDecimal> values) {
            addCriterion("XXLX in", values, "xxlx");
            return (Criteria) this;
        }

        public Criteria andXxlxNotIn(List<BigDecimal> values) {
            addCriterion("XXLX not in", values, "xxlx");
            return (Criteria) this;
        }

        public Criteria andXxlxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("XXLX between", value1, value2, "xxlx");
            return (Criteria) this;
        }

        public Criteria andXxlxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("XXLX not between", value1, value2, "xxlx");
            return (Criteria) this;
        }

        public Criteria andBjsjIsNull() {
            addCriterion("BJSJ is null");
            return (Criteria) this;
        }

        public Criteria andBjsjIsNotNull() {
            addCriterion("BJSJ is not null");
            return (Criteria) this;
        }

        public Criteria andBjsjEqualTo(String value) {
            addCriterion("BJSJ =", value, "bjsj");
            return (Criteria) this;
        }

        public Criteria andBjsjNotEqualTo(String value) {
            addCriterion("BJSJ <>", value, "bjsj");
            return (Criteria) this;
        }

        public Criteria andBjsjGreaterThan(String value) {
            addCriterion("BJSJ >", value, "bjsj");
            return (Criteria) this;
        }

        public Criteria andBjsjGreaterThanOrEqualTo(String value) {
            addCriterion("BJSJ >=", value, "bjsj");
            return (Criteria) this;
        }

        public Criteria andBjsjLessThan(String value) {
            addCriterion("BJSJ <", value, "bjsj");
            return (Criteria) this;
        }

        public Criteria andBjsjLessThanOrEqualTo(String value) {
            addCriterion("BJSJ <=", value, "bjsj");
            return (Criteria) this;
        }

        public Criteria andBjsjLike(String value) {
            addCriterion("BJSJ like", value, "bjsj");
            return (Criteria) this;
        }

        public Criteria andBjsjNotLike(String value) {
            addCriterion("BJSJ not like", value, "bjsj");
            return (Criteria) this;
        }

        public Criteria andBjsjIn(List<String> values) {
            addCriterion("BJSJ in", values, "bjsj");
            return (Criteria) this;
        }

        public Criteria andBjsjNotIn(List<String> values) {
            addCriterion("BJSJ not in", values, "bjsj");
            return (Criteria) this;
        }

        public Criteria andBjsjBetween(String value1, String value2) {
            addCriterion("BJSJ between", value1, value2, "bjsj");
            return (Criteria) this;
        }

        public Criteria andBjsjNotBetween(String value1, String value2) {
            addCriterion("BJSJ not between", value1, value2, "bjsj");
            return (Criteria) this;
        }

        public Criteria andRwIdIsNull() {
            addCriterion("RW_ID is null");
            return (Criteria) this;
        }

        public Criteria andRwIdIsNotNull() {
            addCriterion("RW_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRwIdEqualTo(String value) {
            addCriterion("RW_ID =", value, "rwId");
            return (Criteria) this;
        }

        public Criteria andRwIdNotEqualTo(String value) {
            addCriterion("RW_ID <>", value, "rwId");
            return (Criteria) this;
        }

        public Criteria andRwIdGreaterThan(String value) {
            addCriterion("RW_ID >", value, "rwId");
            return (Criteria) this;
        }

        public Criteria andRwIdGreaterThanOrEqualTo(String value) {
            addCriterion("RW_ID >=", value, "rwId");
            return (Criteria) this;
        }

        public Criteria andRwIdLessThan(String value) {
            addCriterion("RW_ID <", value, "rwId");
            return (Criteria) this;
        }

        public Criteria andRwIdLessThanOrEqualTo(String value) {
            addCriterion("RW_ID <=", value, "rwId");
            return (Criteria) this;
        }

        public Criteria andRwIdLike(String value) {
            addCriterion("RW_ID like", value, "rwId");
            return (Criteria) this;
        }

        public Criteria andRwIdNotLike(String value) {
            addCriterion("RW_ID not like", value, "rwId");
            return (Criteria) this;
        }

        public Criteria andRwIdIn(List<String> values) {
            addCriterion("RW_ID in", values, "rwId");
            return (Criteria) this;
        }

        public Criteria andRwIdNotIn(List<String> values) {
            addCriterion("RW_ID not in", values, "rwId");
            return (Criteria) this;
        }

        public Criteria andRwIdBetween(String value1, String value2) {
            addCriterion("RW_ID between", value1, value2, "rwId");
            return (Criteria) this;
        }

        public Criteria andRwIdNotBetween(String value1, String value2) {
            addCriterion("RW_ID not between", value1, value2, "rwId");
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