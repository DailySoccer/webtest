package unusual.pages.components

import unusual.model.Resolution
import unusual.pages.SharedPage

class ContestDescriptionWindow(res:Resolution) extends SharedPage(res) {

  override def open = {
    logger.error("Trying to open a control.")
    this
  }

  override def isAt = {
    placeholder
    true
  }

}
