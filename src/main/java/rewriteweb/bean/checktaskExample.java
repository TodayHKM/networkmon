package rewriteweb.bean;

import java.util.ArrayList;
import java.util.List;

public class checktaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public checktaskExample() {
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

        public Criteria andWzztIsNull() {
            addCriterion("WZZT is null");
            return (Criteria) this;
        }

        public Criteria andWzztIsNotNull() {
            addCriterion("WZZT is not null");
            return (Criteria) this;
        }

        public Criteria andWzztEqualTo(String value) {
            addCriterion("WZZT =", value, "wzzt");
            return (Criteria) this;
        }

        public Criteria andWzztNotEqualTo(String value) {
            addCriterion("WZZT <>", value, "wzzt");
            return (Criteria) this;
        }

        public Criteria andWzztGreaterThan(String value) {
            addCriterion("WZZT >", value, "wzzt");
            return (Criteria) this;
        }

        public Criteria andWzztGreaterThanOrEqualTo(String value) {
            addCriterion("WZZT >=", value, "wzzt");
            return (Criteria) this;
        }

        public Criteria andWzztLessThan(String value) {
            addCriterion("WZZT <", value, "wzzt");
            return (Criteria) this;
        }

        public Criteria andWzztLessThanOrEqualTo(String value) {
            addCriterion("WZZT <=", value, "wzzt");
            return (Criteria) this;
        }

        public Criteria andWzztLike(String value) {
            addCriterion("WZZT like", value, "wzzt");
            return (Criteria) this;
        }

        public Criteria andWzztNotLike(String value) {
            addCriterion("WZZT not like", value, "wzzt");
            return (Criteria) this;
        }

        public Criteria andWzztIn(List<String> values) {
            addCriterion("WZZT in", values, "wzzt");
            return (Criteria) this;
        }

        public Criteria andWzztNotIn(List<String> values) {
            addCriterion("WZZT not in", values, "wzzt");
            return (Criteria) this;
        }

        public Criteria andWzztBetween(String value1, String value2) {
            addCriterion("WZZT between", value1, value2, "wzzt");
            return (Criteria) this;
        }

        public Criteria andWzztNotBetween(String value1, String value2) {
            addCriterion("WZZT not between", value1, value2, "wzzt");
            return (Criteria) this;
        }

        public Criteria andUrlYccsIsNull() {
            addCriterion("URL_YCCS is null");
            return (Criteria) this;
        }

        public Criteria andUrlYccsIsNotNull() {
            addCriterion("URL_YCCS is not null");
            return (Criteria) this;
        }

        public Criteria andUrlYccsEqualTo(Long value) {
            addCriterion("URL_YCCS =", value, "urlYccs");
            return (Criteria) this;
        }

        public Criteria andUrlYccsNotEqualTo(Long value) {
            addCriterion("URL_YCCS <>", value, "urlYccs");
            return (Criteria) this;
        }

        public Criteria andUrlYccsGreaterThan(Long value) {
            addCriterion("URL_YCCS >", value, "urlYccs");
            return (Criteria) this;
        }

        public Criteria andUrlYccsGreaterThanOrEqualTo(Long value) {
            addCriterion("URL_YCCS >=", value, "urlYccs");
            return (Criteria) this;
        }

        public Criteria andUrlYccsLessThan(Long value) {
            addCriterion("URL_YCCS <", value, "urlYccs");
            return (Criteria) this;
        }

        public Criteria andUrlYccsLessThanOrEqualTo(Long value) {
            addCriterion("URL_YCCS <=", value, "urlYccs");
            return (Criteria) this;
        }

        public Criteria andUrlYccsIn(List<Long> values) {
            addCriterion("URL_YCCS in", values, "urlYccs");
            return (Criteria) this;
        }

        public Criteria andUrlYccsNotIn(List<Long> values) {
            addCriterion("URL_YCCS not in", values, "urlYccs");
            return (Criteria) this;
        }

        public Criteria andUrlYccsBetween(Long value1, Long value2) {
            addCriterion("URL_YCCS between", value1, value2, "urlYccs");
            return (Criteria) this;
        }

        public Criteria andUrlYccsNotBetween(Long value1, Long value2) {
            addCriterion("URL_YCCS not between", value1, value2, "urlYccs");
            return (Criteria) this;
        }

        public Criteria andYmjzyccsIsNull() {
            addCriterion("YMJZYCCS is null");
            return (Criteria) this;
        }

        public Criteria andYmjzyccsIsNotNull() {
            addCriterion("YMJZYCCS is not null");
            return (Criteria) this;
        }

        public Criteria andYmjzyccsEqualTo(Long value) {
            addCriterion("YMJZYCCS =", value, "ymjzyccs");
            return (Criteria) this;
        }

        public Criteria andYmjzyccsNotEqualTo(Long value) {
            addCriterion("YMJZYCCS <>", value, "ymjzyccs");
            return (Criteria) this;
        }

        public Criteria andYmjzyccsGreaterThan(Long value) {
            addCriterion("YMJZYCCS >", value, "ymjzyccs");
            return (Criteria) this;
        }

        public Criteria andYmjzyccsGreaterThanOrEqualTo(Long value) {
            addCriterion("YMJZYCCS >=", value, "ymjzyccs");
            return (Criteria) this;
        }

        public Criteria andYmjzyccsLessThan(Long value) {
            addCriterion("YMJZYCCS <", value, "ymjzyccs");
            return (Criteria) this;
        }

        public Criteria andYmjzyccsLessThanOrEqualTo(Long value) {
            addCriterion("YMJZYCCS <=", value, "ymjzyccs");
            return (Criteria) this;
        }

        public Criteria andYmjzyccsIn(List<Long> values) {
            addCriterion("YMJZYCCS in", values, "ymjzyccs");
            return (Criteria) this;
        }

        public Criteria andYmjzyccsNotIn(List<Long> values) {
            addCriterion("YMJZYCCS not in", values, "ymjzyccs");
            return (Criteria) this;
        }

        public Criteria andYmjzyccsBetween(Long value1, Long value2) {
            addCriterion("YMJZYCCS between", value1, value2, "ymjzyccs");
            return (Criteria) this;
        }

        public Criteria andYmjzyccsNotBetween(Long value1, Long value2) {
            addCriterion("YMJZYCCS not between", value1, value2, "ymjzyccs");
            return (Criteria) this;
        }

        public Criteria andLjcsyccsIsNull() {
            addCriterion("LJCSYCCS is null");
            return (Criteria) this;
        }

        public Criteria andLjcsyccsIsNotNull() {
            addCriterion("LJCSYCCS is not null");
            return (Criteria) this;
        }

        public Criteria andLjcsyccsEqualTo(Long value) {
            addCriterion("LJCSYCCS =", value, "ljcsyccs");
            return (Criteria) this;
        }

        public Criteria andLjcsyccsNotEqualTo(Long value) {
            addCriterion("LJCSYCCS <>", value, "ljcsyccs");
            return (Criteria) this;
        }

        public Criteria andLjcsyccsGreaterThan(Long value) {
            addCriterion("LJCSYCCS >", value, "ljcsyccs");
            return (Criteria) this;
        }

        public Criteria andLjcsyccsGreaterThanOrEqualTo(Long value) {
            addCriterion("LJCSYCCS >=", value, "ljcsyccs");
            return (Criteria) this;
        }

        public Criteria andLjcsyccsLessThan(Long value) {
            addCriterion("LJCSYCCS <", value, "ljcsyccs");
            return (Criteria) this;
        }

        public Criteria andLjcsyccsLessThanOrEqualTo(Long value) {
            addCriterion("LJCSYCCS <=", value, "ljcsyccs");
            return (Criteria) this;
        }

        public Criteria andLjcsyccsIn(List<Long> values) {
            addCriterion("LJCSYCCS in", values, "ljcsyccs");
            return (Criteria) this;
        }

        public Criteria andLjcsyccsNotIn(List<Long> values) {
            addCriterion("LJCSYCCS not in", values, "ljcsyccs");
            return (Criteria) this;
        }

        public Criteria andLjcsyccsBetween(Long value1, Long value2) {
            addCriterion("LJCSYCCS between", value1, value2, "ljcsyccs");
            return (Criteria) this;
        }

        public Criteria andLjcsyccsNotBetween(Long value1, Long value2) {
            addCriterion("LJCSYCCS not between", value1, value2, "ljcsyccs");
            return (Criteria) this;
        }

        public Criteria andJckssjIsNull() {
            addCriterion("JCKSSJ is null");
            return (Criteria) this;
        }

        public Criteria andJckssjIsNotNull() {
            addCriterion("JCKSSJ is not null");
            return (Criteria) this;
        }

        public Criteria andJckssjEqualTo(String value) {
            addCriterion("JCKSSJ =", value, "jckssj");
            return (Criteria) this;
        }

        public Criteria andJckssjNotEqualTo(String value) {
            addCriterion("JCKSSJ <>", value, "jckssj");
            return (Criteria) this;
        }

        public Criteria andJckssjGreaterThan(String value) {
            addCriterion("JCKSSJ >", value, "jckssj");
            return (Criteria) this;
        }

        public Criteria andJckssjGreaterThanOrEqualTo(String value) {
            addCriterion("JCKSSJ >=", value, "jckssj");
            return (Criteria) this;
        }

        public Criteria andJckssjLessThan(String value) {
            addCriterion("JCKSSJ <", value, "jckssj");
            return (Criteria) this;
        }

        public Criteria andJckssjLessThanOrEqualTo(String value) {
            addCriterion("JCKSSJ <=", value, "jckssj");
            return (Criteria) this;
        }

        public Criteria andJckssjLike(String value) {
            addCriterion("JCKSSJ like", value, "jckssj");
            return (Criteria) this;
        }

        public Criteria andJckssjNotLike(String value) {
            addCriterion("JCKSSJ not like", value, "jckssj");
            return (Criteria) this;
        }

        public Criteria andJckssjIn(List<String> values) {
            addCriterion("JCKSSJ in", values, "jckssj");
            return (Criteria) this;
        }

        public Criteria andJckssjNotIn(List<String> values) {
            addCriterion("JCKSSJ not in", values, "jckssj");
            return (Criteria) this;
        }

        public Criteria andJckssjBetween(String value1, String value2) {
            addCriterion("JCKSSJ between", value1, value2, "jckssj");
            return (Criteria) this;
        }

        public Criteria andJckssjNotBetween(String value1, String value2) {
            addCriterion("JCKSSJ not between", value1, value2, "jckssj");
            return (Criteria) this;
        }

        public Criteria andJcjssjIsNull() {
            addCriterion("JCJSSJ is null");
            return (Criteria) this;
        }

        public Criteria andJcjssjIsNotNull() {
            addCriterion("JCJSSJ is not null");
            return (Criteria) this;
        }

        public Criteria andJcjssjEqualTo(String value) {
            addCriterion("JCJSSJ =", value, "jcjssj");
            return (Criteria) this;
        }

        public Criteria andJcjssjNotEqualTo(String value) {
            addCriterion("JCJSSJ <>", value, "jcjssj");
            return (Criteria) this;
        }

        public Criteria andJcjssjGreaterThan(String value) {
            addCriterion("JCJSSJ >", value, "jcjssj");
            return (Criteria) this;
        }

        public Criteria andJcjssjGreaterThanOrEqualTo(String value) {
            addCriterion("JCJSSJ >=", value, "jcjssj");
            return (Criteria) this;
        }

        public Criteria andJcjssjLessThan(String value) {
            addCriterion("JCJSSJ <", value, "jcjssj");
            return (Criteria) this;
        }

        public Criteria andJcjssjLessThanOrEqualTo(String value) {
            addCriterion("JCJSSJ <=", value, "jcjssj");
            return (Criteria) this;
        }

        public Criteria andJcjssjLike(String value) {
            addCriterion("JCJSSJ like", value, "jcjssj");
            return (Criteria) this;
        }

        public Criteria andJcjssjNotLike(String value) {
            addCriterion("JCJSSJ not like", value, "jcjssj");
            return (Criteria) this;
        }

        public Criteria andJcjssjIn(List<String> values) {
            addCriterion("JCJSSJ in", values, "jcjssj");
            return (Criteria) this;
        }

        public Criteria andJcjssjNotIn(List<String> values) {
            addCriterion("JCJSSJ not in", values, "jcjssj");
            return (Criteria) this;
        }

        public Criteria andJcjssjBetween(String value1, String value2) {
            addCriterion("JCJSSJ between", value1, value2, "jcjssj");
            return (Criteria) this;
        }

        public Criteria andJcjssjNotBetween(String value1, String value2) {
            addCriterion("JCJSSJ not between", value1, value2, "jcjssj");
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