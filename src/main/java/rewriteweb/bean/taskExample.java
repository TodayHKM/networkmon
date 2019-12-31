package rewriteweb.bean;

import java.util.ArrayList;
import java.util.List;

public class taskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public taskExample() {
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

        public Criteria andRwmcIsNull() {
            addCriterion("RWMC is null");
            return (Criteria) this;
        }

        public Criteria andRwmcIsNotNull() {
            addCriterion("RWMC is not null");
            return (Criteria) this;
        }

        public Criteria andRwmcEqualTo(String value) {
            addCriterion("RWMC =", value, "rwmc");
            return (Criteria) this;
        }

        public Criteria andRwmcNotEqualTo(String value) {
            addCriterion("RWMC <>", value, "rwmc");
            return (Criteria) this;
        }

        public Criteria andRwmcGreaterThan(String value) {
            addCriterion("RWMC >", value, "rwmc");
            return (Criteria) this;
        }

        public Criteria andRwmcGreaterThanOrEqualTo(String value) {
            addCriterion("RWMC >=", value, "rwmc");
            return (Criteria) this;
        }

        public Criteria andRwmcLessThan(String value) {
            addCriterion("RWMC <", value, "rwmc");
            return (Criteria) this;
        }

        public Criteria andRwmcLessThanOrEqualTo(String value) {
            addCriterion("RWMC <=", value, "rwmc");
            return (Criteria) this;
        }

        public Criteria andRwmcLike(String value) {
            addCriterion("RWMC like", value, "rwmc");
            return (Criteria) this;
        }

        public Criteria andRwmcNotLike(String value) {
            addCriterion("RWMC not like", value, "rwmc");
            return (Criteria) this;
        }

        public Criteria andRwmcIn(List<String> values) {
            addCriterion("RWMC in", values, "rwmc");
            return (Criteria) this;
        }

        public Criteria andRwmcNotIn(List<String> values) {
            addCriterion("RWMC not in", values, "rwmc");
            return (Criteria) this;
        }

        public Criteria andRwmcBetween(String value1, String value2) {
            addCriterion("RWMC between", value1, value2, "rwmc");
            return (Criteria) this;
        }

        public Criteria andRwmcNotBetween(String value1, String value2) {
            addCriterion("RWMC not between", value1, value2, "rwmc");
            return (Criteria) this;
        }

        public Criteria andJkwzUrlIsNull() {
            addCriterion("JKWZ_URL is null");
            return (Criteria) this;
        }

        public Criteria andJkwzUrlIsNotNull() {
            addCriterion("JKWZ_URL is not null");
            return (Criteria) this;
        }

        public Criteria andJkwzUrlEqualTo(String value) {
            addCriterion("JKWZ_URL =", value, "jkwzUrl");
            return (Criteria) this;
        }

        public Criteria andJkwzUrlNotEqualTo(String value) {
            addCriterion("JKWZ_URL <>", value, "jkwzUrl");
            return (Criteria) this;
        }

        public Criteria andJkwzUrlGreaterThan(String value) {
            addCriterion("JKWZ_URL >", value, "jkwzUrl");
            return (Criteria) this;
        }

        public Criteria andJkwzUrlGreaterThanOrEqualTo(String value) {
            addCriterion("JKWZ_URL >=", value, "jkwzUrl");
            return (Criteria) this;
        }

        public Criteria andJkwzUrlLessThan(String value) {
            addCriterion("JKWZ_URL <", value, "jkwzUrl");
            return (Criteria) this;
        }

        public Criteria andJkwzUrlLessThanOrEqualTo(String value) {
            addCriterion("JKWZ_URL <=", value, "jkwzUrl");
            return (Criteria) this;
        }

        public Criteria andJkwzUrlLike(String value) {
            addCriterion("JKWZ_URL like", value, "jkwzUrl");
            return (Criteria) this;
        }

        public Criteria andJkwzUrlNotLike(String value) {
            addCriterion("JKWZ_URL not like", value, "jkwzUrl");
            return (Criteria) this;
        }

        public Criteria andJkwzUrlIn(List<String> values) {
            addCriterion("JKWZ_URL in", values, "jkwzUrl");
            return (Criteria) this;
        }

        public Criteria andJkwzUrlNotIn(List<String> values) {
            addCriterion("JKWZ_URL not in", values, "jkwzUrl");
            return (Criteria) this;
        }

        public Criteria andJkwzUrlBetween(String value1, String value2) {
            addCriterion("JKWZ_URL between", value1, value2, "jkwzUrl");
            return (Criteria) this;
        }

        public Criteria andJkwzUrlNotBetween(String value1, String value2) {
            addCriterion("JKWZ_URL not between", value1, value2, "jkwzUrl");
            return (Criteria) this;
        }

        public Criteria andFzbmIsNull() {
            addCriterion("FZBM is null");
            return (Criteria) this;
        }

        public Criteria andFzbmIsNotNull() {
            addCriterion("FZBM is not null");
            return (Criteria) this;
        }

        public Criteria andFzbmEqualTo(String value) {
            addCriterion("FZBM =", value, "fzbm");
            return (Criteria) this;
        }

        public Criteria andFzbmNotEqualTo(String value) {
            addCriterion("FZBM <>", value, "fzbm");
            return (Criteria) this;
        }

        public Criteria andFzbmGreaterThan(String value) {
            addCriterion("FZBM >", value, "fzbm");
            return (Criteria) this;
        }

        public Criteria andFzbmGreaterThanOrEqualTo(String value) {
            addCriterion("FZBM >=", value, "fzbm");
            return (Criteria) this;
        }

        public Criteria andFzbmLessThan(String value) {
            addCriterion("FZBM <", value, "fzbm");
            return (Criteria) this;
        }

        public Criteria andFzbmLessThanOrEqualTo(String value) {
            addCriterion("FZBM <=", value, "fzbm");
            return (Criteria) this;
        }

        public Criteria andFzbmLike(String value) {
            addCriterion("FZBM like", value, "fzbm");
            return (Criteria) this;
        }

        public Criteria andFzbmNotLike(String value) {
            addCriterion("FZBM not like", value, "fzbm");
            return (Criteria) this;
        }

        public Criteria andFzbmIn(List<String> values) {
            addCriterion("FZBM in", values, "fzbm");
            return (Criteria) this;
        }

        public Criteria andFzbmNotIn(List<String> values) {
            addCriterion("FZBM not in", values, "fzbm");
            return (Criteria) this;
        }

        public Criteria andFzbmBetween(String value1, String value2) {
            addCriterion("FZBM between", value1, value2, "fzbm");
            return (Criteria) this;
        }

        public Criteria andFzbmNotBetween(String value1, String value2) {
            addCriterion("FZBM not between", value1, value2, "fzbm");
            return (Criteria) this;
        }

        public Criteria andJkplIsNull() {
            addCriterion("JKPL is null");
            return (Criteria) this;
        }

        public Criteria andJkplIsNotNull() {
            addCriterion("JKPL is not null");
            return (Criteria) this;
        }

        public Criteria andJkplEqualTo(Long value) {
            addCriterion("JKPL =", value, "jkpl");
            return (Criteria) this;
        }

        public Criteria andJkplNotEqualTo(Long value) {
            addCriterion("JKPL <>", value, "jkpl");
            return (Criteria) this;
        }

        public Criteria andJkplGreaterThan(Long value) {
            addCriterion("JKPL >", value, "jkpl");
            return (Criteria) this;
        }

        public Criteria andJkplGreaterThanOrEqualTo(Long value) {
            addCriterion("JKPL >=", value, "jkpl");
            return (Criteria) this;
        }

        public Criteria andJkplLessThan(Long value) {
            addCriterion("JKPL <", value, "jkpl");
            return (Criteria) this;
        }

        public Criteria andJkplLessThanOrEqualTo(Long value) {
            addCriterion("JKPL <=", value, "jkpl");
            return (Criteria) this;
        }

        public Criteria andJkplIn(List<Long> values) {
            addCriterion("JKPL in", values, "jkpl");
            return (Criteria) this;
        }

        public Criteria andJkplNotIn(List<Long> values) {
            addCriterion("JKPL not in", values, "jkpl");
            return (Criteria) this;
        }

        public Criteria andJkplBetween(Long value1, Long value2) {
            addCriterion("JKPL between", value1, value2, "jkpl");
            return (Criteria) this;
        }

        public Criteria andJkplNotBetween(Long value1, Long value2) {
            addCriterion("JKPL not between", value1, value2, "jkpl");
            return (Criteria) this;
        }

        public Criteria andJksdIsNull() {
            addCriterion("JKSD is null");
            return (Criteria) this;
        }

        public Criteria andJksdIsNotNull() {
            addCriterion("JKSD is not null");
            return (Criteria) this;
        }

        public Criteria andJksdEqualTo(Long value) {
            addCriterion("JKSD =", value, "jksd");
            return (Criteria) this;
        }

        public Criteria andJksdNotEqualTo(Long value) {
            addCriterion("JKSD <>", value, "jksd");
            return (Criteria) this;
        }

        public Criteria andJksdGreaterThan(Long value) {
            addCriterion("JKSD >", value, "jksd");
            return (Criteria) this;
        }

        public Criteria andJksdGreaterThanOrEqualTo(Long value) {
            addCriterion("JKSD >=", value, "jksd");
            return (Criteria) this;
        }

        public Criteria andJksdLessThan(Long value) {
            addCriterion("JKSD <", value, "jksd");
            return (Criteria) this;
        }

        public Criteria andJksdLessThanOrEqualTo(Long value) {
            addCriterion("JKSD <=", value, "jksd");
            return (Criteria) this;
        }

        public Criteria andJksdIn(List<Long> values) {
            addCriterion("JKSD in", values, "jksd");
            return (Criteria) this;
        }

        public Criteria andJksdNotIn(List<Long> values) {
            addCriterion("JKSD not in", values, "jksd");
            return (Criteria) this;
        }

        public Criteria andJksdBetween(Long value1, Long value2) {
            addCriterion("JKSD between", value1, value2, "jksd");
            return (Criteria) this;
        }

        public Criteria andJksdNotBetween(Long value1, Long value2) {
            addCriterion("JKSD not between", value1, value2, "jksd");
            return (Criteria) this;
        }

        public Criteria andCsszIsNull() {
            addCriterion("CSSZ is null");
            return (Criteria) this;
        }

        public Criteria andCsszIsNotNull() {
            addCriterion("CSSZ is not null");
            return (Criteria) this;
        }

        public Criteria andCsszEqualTo(Long value) {
            addCriterion("CSSZ =", value, "cssz");
            return (Criteria) this;
        }

        public Criteria andCsszNotEqualTo(Long value) {
            addCriterion("CSSZ <>", value, "cssz");
            return (Criteria) this;
        }

        public Criteria andCsszGreaterThan(Long value) {
            addCriterion("CSSZ >", value, "cssz");
            return (Criteria) this;
        }

        public Criteria andCsszGreaterThanOrEqualTo(Long value) {
            addCriterion("CSSZ >=", value, "cssz");
            return (Criteria) this;
        }

        public Criteria andCsszLessThan(Long value) {
            addCriterion("CSSZ <", value, "cssz");
            return (Criteria) this;
        }

        public Criteria andCsszLessThanOrEqualTo(Long value) {
            addCriterion("CSSZ <=", value, "cssz");
            return (Criteria) this;
        }

        public Criteria andCsszIn(List<Long> values) {
            addCriterion("CSSZ in", values, "cssz");
            return (Criteria) this;
        }

        public Criteria andCsszNotIn(List<Long> values) {
            addCriterion("CSSZ not in", values, "cssz");
            return (Criteria) this;
        }

        public Criteria andCsszBetween(Long value1, Long value2) {
            addCriterion("CSSZ between", value1, value2, "cssz");
            return (Criteria) this;
        }

        public Criteria andCsszNotBetween(Long value1, Long value2) {
            addCriterion("CSSZ not between", value1, value2, "cssz");
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

        public Criteria andCzIsNull() {
            addCriterion("CZ is null");
            return (Criteria) this;
        }

        public Criteria andCzIsNotNull() {
            addCriterion("CZ is not null");
            return (Criteria) this;
        }

        public Criteria andCzEqualTo(String value) {
            addCriterion("CZ =", value, "cz");
            return (Criteria) this;
        }

        public Criteria andCzNotEqualTo(String value) {
            addCriterion("CZ <>", value, "cz");
            return (Criteria) this;
        }

        public Criteria andCzGreaterThan(String value) {
            addCriterion("CZ >", value, "cz");
            return (Criteria) this;
        }

        public Criteria andCzGreaterThanOrEqualTo(String value) {
            addCriterion("CZ >=", value, "cz");
            return (Criteria) this;
        }

        public Criteria andCzLessThan(String value) {
            addCriterion("CZ <", value, "cz");
            return (Criteria) this;
        }

        public Criteria andCzLessThanOrEqualTo(String value) {
            addCriterion("CZ <=", value, "cz");
            return (Criteria) this;
        }

        public Criteria andCzLike(String value) {
            addCriterion("CZ like", value, "cz");
            return (Criteria) this;
        }

        public Criteria andCzNotLike(String value) {
            addCriterion("CZ not like", value, "cz");
            return (Criteria) this;
        }

        public Criteria andCzIn(List<String> values) {
            addCriterion("CZ in", values, "cz");
            return (Criteria) this;
        }

        public Criteria andCzNotIn(List<String> values) {
            addCriterion("CZ not in", values, "cz");
            return (Criteria) this;
        }

        public Criteria andCzBetween(String value1, String value2) {
            addCriterion("CZ between", value1, value2, "cz");
            return (Criteria) this;
        }

        public Criteria andCzNotBetween(String value1, String value2) {
            addCriterion("CZ not between", value1, value2, "cz");
            return (Criteria) this;
        }

        public Criteria andBjjsyxIsNull() {
            addCriterion("BJJSYX is null");
            return (Criteria) this;
        }

        public Criteria andBjjsyxIsNotNull() {
            addCriterion("BJJSYX is not null");
            return (Criteria) this;
        }

        public Criteria andBjjsyxEqualTo(String value) {
            addCriterion("BJJSYX =", value, "bjjsyx");
            return (Criteria) this;
        }

        public Criteria andBjjsyxNotEqualTo(String value) {
            addCriterion("BJJSYX <>", value, "bjjsyx");
            return (Criteria) this;
        }

        public Criteria andBjjsyxGreaterThan(String value) {
            addCriterion("BJJSYX >", value, "bjjsyx");
            return (Criteria) this;
        }

        public Criteria andBjjsyxGreaterThanOrEqualTo(String value) {
            addCriterion("BJJSYX >=", value, "bjjsyx");
            return (Criteria) this;
        }

        public Criteria andBjjsyxLessThan(String value) {
            addCriterion("BJJSYX <", value, "bjjsyx");
            return (Criteria) this;
        }

        public Criteria andBjjsyxLessThanOrEqualTo(String value) {
            addCriterion("BJJSYX <=", value, "bjjsyx");
            return (Criteria) this;
        }

        public Criteria andBjjsyxLike(String value) {
            addCriterion("BJJSYX like", value, "bjjsyx");
            return (Criteria) this;
        }

        public Criteria andBjjsyxNotLike(String value) {
            addCriterion("BJJSYX not like", value, "bjjsyx");
            return (Criteria) this;
        }

        public Criteria andBjjsyxIn(List<String> values) {
            addCriterion("BJJSYX in", values, "bjjsyx");
            return (Criteria) this;
        }

        public Criteria andBjjsyxNotIn(List<String> values) {
            addCriterion("BJJSYX not in", values, "bjjsyx");
            return (Criteria) this;
        }

        public Criteria andBjjsyxBetween(String value1, String value2) {
            addCriterion("BJJSYX between", value1, value2, "bjjsyx");
            return (Criteria) this;
        }

        public Criteria andBjjsyxNotBetween(String value1, String value2) {
            addCriterion("BJJSYX not between", value1, value2, "bjjsyx");
            return (Criteria) this;
        }

        public Criteria andFsyxIsNull() {
            addCriterion("FSYX is null");
            return (Criteria) this;
        }

        public Criteria andFsyxIsNotNull() {
            addCriterion("FSYX is not null");
            return (Criteria) this;
        }

        public Criteria andFsyxEqualTo(String value) {
            addCriterion("FSYX =", value, "fsyx");
            return (Criteria) this;
        }

        public Criteria andFsyxNotEqualTo(String value) {
            addCriterion("FSYX <>", value, "fsyx");
            return (Criteria) this;
        }

        public Criteria andFsyxGreaterThan(String value) {
            addCriterion("FSYX >", value, "fsyx");
            return (Criteria) this;
        }

        public Criteria andFsyxGreaterThanOrEqualTo(String value) {
            addCriterion("FSYX >=", value, "fsyx");
            return (Criteria) this;
        }

        public Criteria andFsyxLessThan(String value) {
            addCriterion("FSYX <", value, "fsyx");
            return (Criteria) this;
        }

        public Criteria andFsyxLessThanOrEqualTo(String value) {
            addCriterion("FSYX <=", value, "fsyx");
            return (Criteria) this;
        }

        public Criteria andFsyxLike(String value) {
            addCriterion("FSYX like", value, "fsyx");
            return (Criteria) this;
        }

        public Criteria andFsyxNotLike(String value) {
            addCriterion("FSYX not like", value, "fsyx");
            return (Criteria) this;
        }

        public Criteria andFsyxIn(List<String> values) {
            addCriterion("FSYX in", values, "fsyx");
            return (Criteria) this;
        }

        public Criteria andFsyxNotIn(List<String> values) {
            addCriterion("FSYX not in", values, "fsyx");
            return (Criteria) this;
        }

        public Criteria andFsyxBetween(String value1, String value2) {
            addCriterion("FSYX between", value1, value2, "fsyx");
            return (Criteria) this;
        }

        public Criteria andFsyxNotBetween(String value1, String value2) {
            addCriterion("FSYX not between", value1, value2, "fsyx");
            return (Criteria) this;
        }

        public Criteria andFsyxmmIsNull() {
            addCriterion("FSYXMM is null");
            return (Criteria) this;
        }

        public Criteria andFsyxmmIsNotNull() {
            addCriterion("FSYXMM is not null");
            return (Criteria) this;
        }

        public Criteria andFsyxmmEqualTo(String value) {
            addCriterion("FSYXMM =", value, "fsyxmm");
            return (Criteria) this;
        }

        public Criteria andFsyxmmNotEqualTo(String value) {
            addCriterion("FSYXMM <>", value, "fsyxmm");
            return (Criteria) this;
        }

        public Criteria andFsyxmmGreaterThan(String value) {
            addCriterion("FSYXMM >", value, "fsyxmm");
            return (Criteria) this;
        }

        public Criteria andFsyxmmGreaterThanOrEqualTo(String value) {
            addCriterion("FSYXMM >=", value, "fsyxmm");
            return (Criteria) this;
        }

        public Criteria andFsyxmmLessThan(String value) {
            addCriterion("FSYXMM <", value, "fsyxmm");
            return (Criteria) this;
        }

        public Criteria andFsyxmmLessThanOrEqualTo(String value) {
            addCriterion("FSYXMM <=", value, "fsyxmm");
            return (Criteria) this;
        }

        public Criteria andFsyxmmLike(String value) {
            addCriterion("FSYXMM like", value, "fsyxmm");
            return (Criteria) this;
        }

        public Criteria andFsyxmmNotLike(String value) {
            addCriterion("FSYXMM not like", value, "fsyxmm");
            return (Criteria) this;
        }

        public Criteria andFsyxmmIn(List<String> values) {
            addCriterion("FSYXMM in", values, "fsyxmm");
            return (Criteria) this;
        }

        public Criteria andFsyxmmNotIn(List<String> values) {
            addCriterion("FSYXMM not in", values, "fsyxmm");
            return (Criteria) this;
        }

        public Criteria andFsyxmmBetween(String value1, String value2) {
            addCriterion("FSYXMM between", value1, value2, "fsyxmm");
            return (Criteria) this;
        }

        public Criteria andFsyxmmNotBetween(String value1, String value2) {
            addCriterion("FSYXMM not between", value1, value2, "fsyxmm");
            return (Criteria) this;
        }

        public Criteria andYxzjIsNull() {
            addCriterion("YXZJ is null");
            return (Criteria) this;
        }

        public Criteria andYxzjIsNotNull() {
            addCriterion("YXZJ is not null");
            return (Criteria) this;
        }

        public Criteria andYxzjEqualTo(String value) {
            addCriterion("YXZJ =", value, "yxzj");
            return (Criteria) this;
        }

        public Criteria andYxzjNotEqualTo(String value) {
            addCriterion("YXZJ <>", value, "yxzj");
            return (Criteria) this;
        }

        public Criteria andYxzjGreaterThan(String value) {
            addCriterion("YXZJ >", value, "yxzj");
            return (Criteria) this;
        }

        public Criteria andYxzjGreaterThanOrEqualTo(String value) {
            addCriterion("YXZJ >=", value, "yxzj");
            return (Criteria) this;
        }

        public Criteria andYxzjLessThan(String value) {
            addCriterion("YXZJ <", value, "yxzj");
            return (Criteria) this;
        }

        public Criteria andYxzjLessThanOrEqualTo(String value) {
            addCriterion("YXZJ <=", value, "yxzj");
            return (Criteria) this;
        }

        public Criteria andYxzjLike(String value) {
            addCriterion("YXZJ like", value, "yxzj");
            return (Criteria) this;
        }

        public Criteria andYxzjNotLike(String value) {
            addCriterion("YXZJ not like", value, "yxzj");
            return (Criteria) this;
        }

        public Criteria andYxzjIn(List<String> values) {
            addCriterion("YXZJ in", values, "yxzj");
            return (Criteria) this;
        }

        public Criteria andYxzjNotIn(List<String> values) {
            addCriterion("YXZJ not in", values, "yxzj");
            return (Criteria) this;
        }

        public Criteria andYxzjBetween(String value1, String value2) {
            addCriterion("YXZJ between", value1, value2, "yxzj");
            return (Criteria) this;
        }

        public Criteria andYxzjNotBetween(String value1, String value2) {
            addCriterion("YXZJ not between", value1, value2, "yxzj");
            return (Criteria) this;
        }

        public Criteria andBjdhIsNull() {
            addCriterion("BJDH is null");
            return (Criteria) this;
        }

        public Criteria andBjdhIsNotNull() {
            addCriterion("BJDH is not null");
            return (Criteria) this;
        }

        public Criteria andBjdhEqualTo(String value) {
            addCriterion("BJDH =", value, "bjdh");
            return (Criteria) this;
        }

        public Criteria andBjdhNotEqualTo(String value) {
            addCriterion("BJDH <>", value, "bjdh");
            return (Criteria) this;
        }

        public Criteria andBjdhGreaterThan(String value) {
            addCriterion("BJDH >", value, "bjdh");
            return (Criteria) this;
        }

        public Criteria andBjdhGreaterThanOrEqualTo(String value) {
            addCriterion("BJDH >=", value, "bjdh");
            return (Criteria) this;
        }

        public Criteria andBjdhLessThan(String value) {
            addCriterion("BJDH <", value, "bjdh");
            return (Criteria) this;
        }

        public Criteria andBjdhLessThanOrEqualTo(String value) {
            addCriterion("BJDH <=", value, "bjdh");
            return (Criteria) this;
        }

        public Criteria andBjdhLike(String value) {
            addCriterion("BJDH like", value, "bjdh");
            return (Criteria) this;
        }

        public Criteria andBjdhNotLike(String value) {
            addCriterion("BJDH not like", value, "bjdh");
            return (Criteria) this;
        }

        public Criteria andBjdhIn(List<String> values) {
            addCriterion("BJDH in", values, "bjdh");
            return (Criteria) this;
        }

        public Criteria andBjdhNotIn(List<String> values) {
            addCriterion("BJDH not in", values, "bjdh");
            return (Criteria) this;
        }

        public Criteria andBjdhBetween(String value1, String value2) {
            addCriterion("BJDH between", value1, value2, "bjdh");
            return (Criteria) this;
        }

        public Criteria andBjdhNotBetween(String value1, String value2) {
            addCriterion("BJDH not between", value1, value2, "bjdh");
            return (Criteria) this;
        }

        public Criteria andSfyxbjIsNull() {
            addCriterion("SFYXBJ is null");
            return (Criteria) this;
        }

        public Criteria andSfyxbjIsNotNull() {
            addCriterion("SFYXBJ is not null");
            return (Criteria) this;
        }

        public Criteria andSfyxbjEqualTo(String value) {
            addCriterion("SFYXBJ =", value, "sfyxbj");
            return (Criteria) this;
        }

        public Criteria andSfyxbjNotEqualTo(String value) {
            addCriterion("SFYXBJ <>", value, "sfyxbj");
            return (Criteria) this;
        }

        public Criteria andSfyxbjGreaterThan(String value) {
            addCriterion("SFYXBJ >", value, "sfyxbj");
            return (Criteria) this;
        }

        public Criteria andSfyxbjGreaterThanOrEqualTo(String value) {
            addCriterion("SFYXBJ >=", value, "sfyxbj");
            return (Criteria) this;
        }

        public Criteria andSfyxbjLessThan(String value) {
            addCriterion("SFYXBJ <", value, "sfyxbj");
            return (Criteria) this;
        }

        public Criteria andSfyxbjLessThanOrEqualTo(String value) {
            addCriterion("SFYXBJ <=", value, "sfyxbj");
            return (Criteria) this;
        }

        public Criteria andSfyxbjLike(String value) {
            addCriterion("SFYXBJ like", value, "sfyxbj");
            return (Criteria) this;
        }

        public Criteria andSfyxbjNotLike(String value) {
            addCriterion("SFYXBJ not like", value, "sfyxbj");
            return (Criteria) this;
        }

        public Criteria andSfyxbjIn(List<String> values) {
            addCriterion("SFYXBJ in", values, "sfyxbj");
            return (Criteria) this;
        }

        public Criteria andSfyxbjNotIn(List<String> values) {
            addCriterion("SFYXBJ not in", values, "sfyxbj");
            return (Criteria) this;
        }

        public Criteria andSfyxbjBetween(String value1, String value2) {
            addCriterion("SFYXBJ between", value1, value2, "sfyxbj");
            return (Criteria) this;
        }

        public Criteria andSfyxbjNotBetween(String value1, String value2) {
            addCriterion("SFYXBJ not between", value1, value2, "sfyxbj");
            return (Criteria) this;
        }

        public Criteria andSfdxbjIsNull() {
            addCriterion("SFDXBJ is null");
            return (Criteria) this;
        }

        public Criteria andSfdxbjIsNotNull() {
            addCriterion("SFDXBJ is not null");
            return (Criteria) this;
        }

        public Criteria andSfdxbjEqualTo(String value) {
            addCriterion("SFDXBJ =", value, "sfdxbj");
            return (Criteria) this;
        }

        public Criteria andSfdxbjNotEqualTo(String value) {
            addCriterion("SFDXBJ <>", value, "sfdxbj");
            return (Criteria) this;
        }

        public Criteria andSfdxbjGreaterThan(String value) {
            addCriterion("SFDXBJ >", value, "sfdxbj");
            return (Criteria) this;
        }

        public Criteria andSfdxbjGreaterThanOrEqualTo(String value) {
            addCriterion("SFDXBJ >=", value, "sfdxbj");
            return (Criteria) this;
        }

        public Criteria andSfdxbjLessThan(String value) {
            addCriterion("SFDXBJ <", value, "sfdxbj");
            return (Criteria) this;
        }

        public Criteria andSfdxbjLessThanOrEqualTo(String value) {
            addCriterion("SFDXBJ <=", value, "sfdxbj");
            return (Criteria) this;
        }

        public Criteria andSfdxbjLike(String value) {
            addCriterion("SFDXBJ like", value, "sfdxbj");
            return (Criteria) this;
        }

        public Criteria andSfdxbjNotLike(String value) {
            addCriterion("SFDXBJ not like", value, "sfdxbj");
            return (Criteria) this;
        }

        public Criteria andSfdxbjIn(List<String> values) {
            addCriterion("SFDXBJ in", values, "sfdxbj");
            return (Criteria) this;
        }

        public Criteria andSfdxbjNotIn(List<String> values) {
            addCriterion("SFDXBJ not in", values, "sfdxbj");
            return (Criteria) this;
        }

        public Criteria andSfdxbjBetween(String value1, String value2) {
            addCriterion("SFDXBJ between", value1, value2, "sfdxbj");
            return (Criteria) this;
        }

        public Criteria andSfdxbjNotBetween(String value1, String value2) {
            addCriterion("SFDXBJ not between", value1, value2, "sfdxbj");
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