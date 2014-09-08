package unusual.pages

import unusual.model.Resolution

class FooterBar(res:Resolution) extends SharedPage {
  val url = SharedPage.baseUrl
  val resolution: Resolution = res

  val HELP_ID  = "footerHelp"
  val LEGAL_ID  = "footerLegal"
  val TERMS_OF_USE_ID  = "footerTermsOfUse"
  val PRIVACY_POLICY_ID  = "footerPrivacyPolicy"

  override def isAt = {
    placeholder

    true
  }


}


