import { defineConfig, loadEnv } from 'vite'
import createVitePlugins from './vite'
import * as path from 'path'
// https://vitejs.dev/config/
export default defineConfig(({
  mode,
  command
}) => {
  const env = loadEnv(mode, process.cwd())
  return {
    plugins: createVitePlugins(env, command === 'build'),
    resolve: {
      alias: {
        '~': path.resolve(__dirname, './'),
        '@': path.resolve(__dirname, 'src')
      },
      // https://cn.vitejs.dev/config/#resolve-extensions
      extensions: ['.mjs', '.js', '.jsx', '.ts', '.tsx', '.json', '.sass', '.scss', '.vue']
    },
    build: {
      outDir: '../src/main/resources/static'
    }
  }
})
