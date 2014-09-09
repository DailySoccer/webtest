package unusual.pages.components

import unusual.model.Resolution
import unusual.pages.SharedPage

class FooterBar(res:Resolution) extends SharedPage(res) {

  val HELP_ID  = "footerHelp"
  val LEGAL_ID  = "footerLegal"
  val TERMS_OF_USE_ID  = "footerTermsOfUse"
  val PRIVACY_POLICY_ID  = "footerPrivacyPolicy"

  override def isAt = {
    placeholder

    true
  }

  override def open = {
    logger.error("Trying to open a control.")
    this
  }


}


