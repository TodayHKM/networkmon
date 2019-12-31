package rewriteweb.bean;

import java.util.ArrayList;
import java.util.List;

public class pagechecksExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public pagechecksExample() {
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

        public Criteria andYurlIdIsNull() {
            addCriterion("YURL_ID is null");
            return (Criteria) this;
        }

        public Criteria andYurlIdIsNotNull() {
            addCriterion("YURL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andYurlIdEqualTo(String value) {
            addCriterion("YURL_ID =", value, "yurlId");
            return (Criteria) this;
        }

        public Criteria andYurlIdNotEqualTo(String value) {
            addCriterion("YURL_ID <>", value, "yurlId");
            return (Criteria) this;
        }

        public Criteria andYurlIdGreaterThan(String value) {
            addCriterion("YURL_ID >", value, "yurlId");
            return (Criteria) this;
        }

        public Criteria andYurlIdGreaterThanOrEqualTo(String value) {
            addCriterion("YURL_ID >=", value, "yurlId");
            return (Criteria) this;
        }

        public Criteria andYurlIdLessThan(String value) {
            addCriterion("YURL_ID <", value, "yurlId");
            return (Criteria) this;
        }

        public Criteria andYurlIdLessThanOrEqualTo(String value) {
            addCriterion("YURL_ID <=", value, "yurlId");
            return (Criteria) this;
        }

        public Criteria andYurlIdLike(String value) {
            addCriterion("YURL_ID like", value, "yurlId");
            return (Criteria) this;
        }

        public Criteria andYurlIdNotLike(String value) {
            addCriterion("YURL_ID not like", value, "yurlId");
            return (Criteria) this;
        }

        public Criteria andYurlIdIn(List<String> values) {
            addCriterion("YURL_ID in", values, "yurlId");
            return (Criteria) this;
        }

        public Criteria andYurlIdNotIn(List<String> values) {
            addCriterion("YURL_ID not in", values, "yurlId");
            return (Criteria) this;
        }

        public Criteria andYurlIdBetween(String value1, String value2) {
            addCriterion("YURL_ID between", value1, value2, "yurlId");
            return (Criteria) this;
        }

        public Criteria andYurlIdNotBetween(String value1, String value2) {
            addCriterion("YURL_ID not between", value1, value2, "yurlId");
            return (Criteria) this;
        }

        public Criteria andYbtIsNull() {
            addCriterion("YBT is null");
            return (Criteria) this;
        }

        public Criteria andYbtIsNotNull() {
            addCriterion("YBT is not null");
            return (Criteria) this;
        }

        public Criteria andYbtEqualTo(String value) {
            addCriterion("YBT =", value, "ybt");
            return (Criteria) this;
        }

        public Criteria andYbtNotEqualTo(String value) {
            addCriterion("YBT <>", value, "ybt");
            return (Criteria) this;
        }

        public Criteria andYbtGreaterThan(String value) {
            addCriterion("YBT >", value, "ybt");
            return (Criteria) this;
        }

        public Criteria andYbtGreaterThanOrEqualTo(String value) {
            addCriterion("YBT >=", value, "ybt");
            return (Criteria) this;
        }

        public Criteria andYbtLessThan(String value) {
            addCriterion("YBT <", value, "ybt");
            return (Criteria) this;
        }

        public Criteria andYbtLessThanOrEqualTo(String value) {
            addCriterion("YBT <=", value, "ybt");
            return (Criteria) this;
        }

        public Criteria andYbtLike(String value) {
            addCriterion("YBT like", value, "ybt");
            return (Criteria) this;
        }

        public Criteria andYbtNotLike(String value) {
            addCriterion("YBT not like", value, "ybt");
            return (Criteria) this;
        }

        public Criteria andYbtIn(List<String> values) {
            addCriterion("YBT in", values, "ybt");
            return (Criteria) this;
        }

        public Criteria andYbtNotIn(List<String> values) {
            addCriterion("YBT not in", values, "ybt");
            return (Criteria) this;
        }

        public Criteria andYbtBetween(String value1, String value2) {
            addCriterion("YBT between", value1, value2, "ybt");
            return (Criteria) this;
        }

        public Criteria andYbtNotBetween(String value1, String value2) {
            addCriterion("YBT not between", value1, value2, "ybt");
            return (Criteria) this;
        }

        public Criteria andYlxIsNull() {
            addCriterion("YLX is null");
            return (Criteria) this;
        }

        public Criteria andYlxIsNotNull() {
            addCriterion("YLX is not null");
            return (Criteria) this;
        }

        public Criteria andYlxEqualTo(Long value) {
            addCriterion("YLX =", value, "ylx");
            return (Criteria) this;
        }

        public Criteria andYlxNotEqualTo(Long value) {
            addCriterion("YLX <>", value, "ylx");
            return (Criteria) this;
        }

        public Criteria andYlxGreaterThan(Long value) {
            addCriterion("YLX >", value, "ylx");
            return (Criteria) this;
        }

        public Criteria andYlxGreaterThanOrEqualTo(Long value) {
            addCriterion("YLX >=", value, "ylx");
            return (Criteria) this;
        }

        public Criteria andYlxLessThan(Long value) {
            addCriterion("YLX <", value, "ylx");
            return (Criteria) this;
        }

        public Criteria andYlxLessThanOrEqualTo(Long value) {
            addCriterion("YLX <=", value, "ylx");
            return (Criteria) this;
        }

        public Criteria andYlxIn(List<Long> values) {
            addCriterion("YLX in", values, "ylx");
            return (Criteria) this;
        }

        public Criteria andYlxNotIn(List<Long> values) {
            addCriterion("YLX not in", values, "ylx");
            return (Criteria) this;
        }

        public Criteria andYlxBetween(Long value1, Long value2) {
            addCriterion("YLX between", value1, value2, "ylx");
            return (Criteria) this;
        }

        public Criteria andYlxNotBetween(Long value1, Long value2) {
            addCriterion("YLX not between", value1, value2, "ylx");
            return (Criteria) this;
        }

        public Criteria andYjzztIsNull() {
            addCriterion("YJZZT is null");
            return (Criteria) this;
        }

        public Criteria andYjzztIsNotNull() {
            addCriterion("YJZZT is not null");
            return (Criteria) this;
        }

        public Criteria andYjzztEqualTo(String value) {
            addCriterion("YJZZT =", value, "yjzzt");
            return (Criteria) this;
        }

        public Criteria andYjzztNotEqualTo(String value) {
            addCriterion("YJZZT <>", value, "yjzzt");
            return (Criteria) this;
        }

        public Criteria andYjzztGreaterThan(String value) {
            addCriterion("YJZZT >", value, "yjzzt");
            return (Criteria) this;
        }

        public Criteria andYjzztGreaterThanOrEqualTo(String value) {
            addCriterion("YJZZT >=", value, "yjzzt");
            return (Criteria) this;
        }

        public Criteria andYjzztLessThan(String value) {
            addCriterion("YJZZT <", value, "yjzzt");
            return (Criteria) this;
        }

        public Criteria andYjzztLessThanOrEqualTo(String value) {
            addCriterion("YJZZT <=", value, "yjzzt");
            return (Criteria) this;
        }

        public Criteria andYjzztLike(String value) {
            addCriterion("YJZZT like", value, "yjzzt");
            return (Criteria) this;
        }

        public Criteria andYjzztNotLike(String value) {
            addCriterion("YJZZT not like", value, "yjzzt");
            return (Criteria) this;
        }

        public Criteria andYjzztIn(List<String> values) {
            addCriterion("YJZZT in", values, "yjzzt");
            return (Criteria) this;
        }

        public Criteria andYjzztNotIn(List<String> values) {
            addCriterion("YJZZT not in", values, "yjzzt");
            return (Criteria) this;
        }

        public Criteria andYjzztBetween(String value1, String value2) {
            addCriterion("YJZZT between", value1, value2, "yjzzt");
            return (Criteria) this;
        }

        public Criteria andYjzztNotBetween(String value1, String value2) {
            addCriterion("YJZZT not between", value1, value2, "yjzzt");
            return (Criteria) this;
        }

        public Criteria andYnhlsIsNull() {
            addCriterion("YNHLS is null");
            return (Criteria) this;
        }

        public Criteria andYnhlsIsNotNull() {
            addCriterion("YNHLS is not null");
            return (Criteria) this;
        }

        public Criteria andYnhlsEqualTo(Long value) {
            addCriterion("YNHLS =", value, "ynhls");
            return (Criteria) this;
        }

        public Criteria andYnhlsNotEqualTo(Long value) {
            addCriterion("YNHLS <>", value, "ynhls");
            return (Criteria) this;
        }

        public Criteria andYnhlsGreaterThan(Long value) {
            addCriterion("YNHLS >", value, "ynhls");
            return (Criteria) this;
        }

        public Criteria andYnhlsGreaterThanOrEqualTo(Long value) {
            addCriterion("YNHLS >=", value, "ynhls");
            return (Criteria) this;
        }

        public Criteria andYnhlsLessThan(Long value) {
            addCriterion("YNHLS <", value, "ynhls");
            return (Criteria) this;
        }

        public Criteria andYnhlsLessThanOrEqualTo(Long value) {
            addCriterion("YNHLS <=", value, "ynhls");
            return (Criteria) this;
        }

        public Criteria andYnhlsIn(List<Long> values) {
            addCriterion("YNHLS in", values, "ynhls");
            return (Criteria) this;
        }

        public Criteria andYnhlsNotIn(List<Long> values) {
            addCriterion("YNHLS not in", values, "ynhls");
            return (Criteria) this;
        }

        public Criteria andYnhlsBetween(Long value1, Long value2) {
            addCriterion("YNHLS between", value1, value2, "ynhls");
            return (Criteria) this;
        }

        public Criteria andYnhlsNotBetween(Long value1, Long value2) {
            addCriterion("YNHLS not between", value1, value2, "ynhls");
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