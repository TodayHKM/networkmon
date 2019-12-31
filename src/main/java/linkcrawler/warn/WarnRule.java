package linkcrawler.warn;

import linkcrawler.datatypes.Warn;

/*
 * 报警规则策略接口
 */
public interface WarnRule {
  String getWarnInfo();
  Warn getWarn() ;
}
