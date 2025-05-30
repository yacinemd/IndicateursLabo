// Karma configuration file, see link for more information
// https://karma-runner.github.io/1.0/config/configuration-file.html

module.exports = function (config) {
  config.set({
    basePath: '',
    frameworks: ['jasmine', '@angular-devkit/build-angular'],
    plugins: [
      require('karma-jasmine'),
      require('karma-chrome-launcher'),
      require('karma-jasmine-html-reporter'),
      require('@angular-devkit/build-angular/plugins/karma'),
      require('karma-webpack') // Ajoutez cette ligne si vous voulez utiliser webpack.
    ],
    client: {
      clearContext: false // leave Jasmine Spec  Runner output visible in browser
    },
    jasmineHtmlReporter: {
      suppressAll: true // removes the duplicated traces
    },
    coverageReporter: {
      dir: require('path').join(__dirname, './coverage/profanis-yt'),
      subdir: '.',
      reporters: [
        { type: 'html' },
        { type: 'text-summary' }
      ]
    },
    reporters: ['progress', 'kjhtml'],
    port: 9876,
    colors: true,
    logLevel: config.LOG_INFO,
    autoWatch: true,
    browsers: ['Chrome', "ChromeHeadlessCI"],
    singleRun: false,
    restartOnFileChange: true,
    customLaunchers: {
      ChromeHeadlessCI: {
        base: 'ChromeHeadless',
        flags: [
          '--no-sandbox',       // Ajoute l'option pour exécuter Chrome en root
          '--disable-gpu',      // Désactive l'accélération GPU
          '--headless',         // Assure que Chrome fonctionne sans interface graphique
          '--remote-debugging-port=9222' // Port pour le débogage (utile dans certains cas)
        ]
      }
    }
  });
};
