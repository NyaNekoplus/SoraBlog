// Pathify
import { make } from 'vuex-pathify'

// Data
const state = {
  drawer: null,
  drawerImage: true,
  mini: false,
  items: [
    {
      title: 'Dashboard',
      icon: 'mdi-view-dashboard',
      to: '/',
    },
    {
      title: 'Writing',
      icon: 'mdi-format-font',
      to: '/writing',
    },
    {
      title: 'Users',
      icon: 'mdi-account',
      to: '/users',
    },
    {
      title: 'Article Table',
      icon: 'mdi-clipboard-outline',
      to: '/articles',
    },
    {
      title: 'Profile',
      icon: 'mdi-account',
      to: '/profile',
    },
    {
      title: 'Image',
      icon: 'file-image',
      items: [
        {
          title: 'Image Classification',
          icon: 'sort-variant',
          to: '/image/classification',
        },
        {
          title: 'Image List',
          icon: 'folder-multiple-image',
          to: '/image/list',
        },
      ]
    },
    {
      title: 'System',
      icon: 'application-cog',
      items: [
        {
          title: 'System Config',
          icon: 'sort-variant',
          to: '/system/config',
        },
        {
          title: 'Website',
          icon: 'folder-multiple-image',
          to: '/website/config',
        },
      ]
    },
    {
      title: 'Regular Tables',
      icon: 'mdi-clipboard-outline',
      to: '/tables/regular/',
    },
    {
      title: 'Typography',
      icon: 'mdi-format-font',
      to: '/components/typography/',
    },
    {
      title: 'Icons',
      icon: 'mdi-chart-bubble',
      to: '/components/icons/',
    },
    {
      title: 'Google Maps',
      icon: 'mdi-map-marker',
      to: '/maps/google/',
    },
    {
      title: 'Notifications',
      icon: 'mdi-bell',
      to: '/components/notifications/',
    },
  ],
}

const mutations = make.mutations(state)

const actions = {
  ...make.actions(state),
  init: async ({ dispatch }) => {
    //
  },
}

const getters = {}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters,
}
