const path = require('path');

module.exports = {
  mode: 'development',
  resolve: {
    extensions: ['.ts', '.js'],
  },
  module: {
    rules: [
      {
        test: /\.ts$/,
        loader: 'ts-loader', // ou 'babel-loader' selon votre setup
        exclude: /node_modules/,
      },
    ],
  },
};
