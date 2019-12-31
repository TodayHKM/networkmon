package rewriteweb.bean;

import java.util.ArrayList;
import java.util.List;

public class specialurlsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public specialurlsExample() {
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

        public Criteria andWzIsNull() {
            addCriterion("WZ is null");
            return (Criteria) this;
        }

        public Criteria andWzIsNotNull() {
            addCriterion("WZ is not null");
            return (Criteria) this;
        }

        public Criteria andWzEqualTo(String value) {
            addCriterion("WZ =", value, "wz");
            return (Criteria) this;
        }

        public Criteria andWzNotEqualTo(String value) {
            addCriterion("WZ <>", value, "wz");
            return (Criteria) this;
        }

        public Criteria andWzGreaterThan(String value) {
            addCriterion("WZ >", value, "wz");
            return (Criteria) this;
        }

        public Criteria andWzGreaterThanOrEqualTo(String value) {
            addCriterion("WZ >=", value, "wz");
            return (Criteria) this;
        }

        public Criteria andWzLessThan(String value) {
            addCriterion("WZ <", value, "wz");
            return (Criteria) this;
        }

        public Criteria andWzLessThanOrEqualTo(String value) {
            addCriterion("WZ <=", value, "wz");
            return (Criteria) this;
        }

        public Criteria andWzLike(String value) {
            addCriterion("WZ like", value, "wz");
            return (Criteria) this;
        }

        public Criteria andWzNotLike(String value) {
            addCriterion("WZ not like", value, "wz");
            return (Criteria) this;
        }

        public Criteria andWzIn(List<String> values) {
            addCriterion("WZ in", values, "wz");
            return (Criteria) this;
        }

        public Criteria andWzNotIn(List<String> values) {
            addCriterion("WZ not in", values, "wz");
            return (Criteria) this;
        }

        public Criteria andWzBetween(String value1, String value2) {
            addCriterion("WZ between", value1, value2, "wz");
            return (Criteria) this;
        }

        public Criteria andWzNotBetween(String value1, String value2) {
            addCriterion("WZ not between", value1, value2, "wz");
            return (Criteria) this;
        }

        public Criteria andMswbIsNull() {
            addCriterion("MSWB is null");
            return (Criteria) this;
        }

        public Criteria andMswbIsNotNull() {
            addCriterion("MSWB is not null");
            return (Criteria) this;
        }

        public Criteria andMswbEqualTo(String value) {
            addCriterion("MSWB =", value, "mswb");
            return (Criteria) this;
        }

        public Criteria andMswbNotEqualTo(String value) {
            addCriterion("MSWB <>", value, "mswb");
            return (Criteria) this;
        }

        public Criteria andMswbGreaterThan(String value) {
            addCriterion("MSWB >", value, "mswb");
            return (Criteria) this;
        }

        public Criteria andMswbGreaterThanOrEqualTo(String value) {
            addCriterion("MSWB >=", value, "mswb");
            return (Criteria) this;
        }

        public Criteria andMswbLessThan(String value) {
            addCriterion("MSWB <", value, "mswb");
            return (Criteria) this;
        }

        public Criteria andMswbLessThanOrEqualTo(String value) {
            addCriterion("MSWB <=", value, "mswb");
            return (Criteria) this;
        }

        public Criteria andMswbLike(String value) {
            addCriterion("MSWB like", value, "mswb");
            return (Criteria) this;
        }

        public Criteria andMswbNotLike(String value) {
            addCriterion("MSWB not like", value, "mswb");
            return (Criteria) this;
        }

        public Criteria andMswbIn(List<String> values) {
            addCriterion("MSWB in", values, "mswb");
            return (Criteria) this;
        }

        public Criteria andMswbNotIn(List<String> values) {
            addCriterion("MSWB not in", values, "mswb");
            return (Criteria) this;
        }

        public Criteria andMswbBetween(String value1, String value2) {
            addCriterion("MSWB between", value1, value2, "mswb");
            return (Criteria) this;
        }

        public Criteria andMswbNotBetween(String value1, String value2) {
            addCriterion("MSWB not between", value1, value2, "mswb");
            return (Criteria) this;
        }

        public Criteria andLxIsNull() {
            addCriterion("LX is null");
            return (Criteria) this;
        }

        public Criteria andLxIsNotNull() {
            addCriterion("LX is not null");
            return (Criteria) this;
        }

        public Criteria andLxEqualTo(Long value) {
            addCriterion("LX =", value, "lx");
            return (Criteria) this;
        }

        public Criteria andLxNotEqualTo(Long value) {
            addCriterion("LX <>", value, "lx");
            return (Criteria) this;
        }

        public Criteria andLxGreaterThan(Long value) {
            addCriterion("LX >", value, "lx");
            return (Criteria) this;
        }

        public Criteria andLxGreaterThanOrEqualTo(Long value) {
            addCriterion("LX >=", value, "lx");
            return (Criteria) this;
        }

        public Criteria andLxLessThan(Long value) {
            addCriterion("LX <", value, "lx");
            return (Criteria) this;
        }

        public Criteria andLxLessThanOrEqualTo(Long value) {
            addCriterion("LX <=", value, "lx");
            return (Criteria) this;
        }

        public Criteria andLxIn(List<Long> values) {
            addCriterion("LX in", values, "lx");
            return (Criteria) this;
        }

        public Criteria andLxNotIn(List<Long> values) {
            addCriterion("LX not in", values, "lx");
            return (Criteria) this;
        }

        public Criteria andLxBetween(Long value1, Long value2) {
            addCriterion("LX between", value1, value2, "lx");
            return (Criteria) this;
        }

        public Criteria andLxNotBetween(Long value1, Long value2) {
            addCriterion("LX not between", value1, value2, "lx");
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