import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import axios from 'axios'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faPaperPlane, faBell, faCoins, faScroll, faUsers, faAlignJustify, faHandPaper, faCircle, faSmile, faStarOfLife, faSignOutAlt, faQuestion, faSpinner, faFan } from '@fortawesome/free-solid-svg-icons'
import { faCity, faCommentAltExclamation, faCommentAltLines, faCommentAltSlash, faMonitorHeartRate, } from '@fortawesome/pro-solid-svg-icons'
import { faScrollOld as falScrollOld, faCaretCircleRight, faCoin, faMicrochip, faChartNetwork, faFileChartLine, faCoins as falCoins} from '@fortawesome/pro-light-svg-icons'
import { faScrollOld as fadScrollOld } from '@fortawesome/pro-duotone-svg-icons'
import { faDiceD20 as fadDiceD20 } from '@fortawesome/pro-duotone-svg-icons'
import { faSword as falSword} from '@fortawesome/pro-light-svg-icons'
import { faSword as fadSword } from '@fortawesome/pro-duotone-svg-icons'
import { faHelmetBattle as falHelmetBattle} from '@fortawesome/pro-light-svg-icons'
import { faHelmetBattle as fadHelmetBattle } from '@fortawesome/pro-duotone-svg-icons'
import { faMinusSquare, faPlusSquare, faTrashAlt, faShieldAlt, faUserShield } from '@fortawesome/pro-light-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import VModal from 'vue-js-modal'
import 'vue-js-modal/dist/styles.css'
import VueDraggable from 'vue-draggable'
import JwPagination from 'jw-vue-pagination'
import VueStomp from "vue-stomp"
import SockJS from "sockjs-client"
import Loading from 'vue-loading-overlay'
import 'vue-loading-overlay/dist/vue-loading.css'
import VueChatScroll from 'vue-chat-scroll'

// import vuetify from '@/plugins/vuetify'

library.add(faPaperPlane, faMicrochip, falCoins, faFileChartLine , faFan, faSpinner, faQuestion, faMonitorHeartRate, faChartNetwork , faBell, faCoin, faCity, faCoins, faMinusSquare, faCommentAltExclamation, faCommentAltLines, faCommentAltSlash, faSignOutAlt, faShieldAlt, faUserShield, faPlusSquare, faTrashAlt, faScroll, faUsers, faCaretCircleRight, faAlignJustify, faHandPaper, faCircle, faSmile, faStarOfLife, falScrollOld, fadScrollOld, falHelmetBattle, fadDiceD20, fadHelmetBattle, falSword, fadSword)

Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.component('jw-pagination', JwPagination)

Vue.use(VModal, { dynamicDefault: { draggable: false, resizable: false }})
Vue.use(VueDraggable)
Vue.use(VueStomp, 'http://localhost:8080/')
Vue.use(SockJS)
Vue.use(Loading)
Vue.use(VueChatScroll)

Vue.config.productionTip = false

axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;

new Vue({
  router,
  store,
  // vuetify,
  render: h => h(App)
}).$mount('#app')
