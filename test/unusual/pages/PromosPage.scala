package unusual.pages

import unusual.model.Resolution

class PromosPage(res:Resolution)  extends SharedPage(res) {
  override val url = SharedPage.baseUrl

  override def isAt = {
    placeholder
    true
  }

}