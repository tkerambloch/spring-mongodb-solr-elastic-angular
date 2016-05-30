gulp = require('gulp')
gutil = require('gulp-util')
coffee = require('gulp-coffee')
uglify = require('gulp-uglify')


paths =
  app: 'src/main/webapp/'
  src: 'src/main/webapp/src/'
  js: 'src/main/webapp/js/'


gulp.task 'coffee', ->
  gulp.src(paths.src + '*.coffee').pipe(coffee(bare: true).on('error', gutil.log)).pipe(uglify()).pipe gulp.dest(paths.js)
  return

gulp.task 'watch', ->
  gulp.watch paths.src + '/*.coffee', { interval: 500 }, [ 'coffee' ]

gulp.task 'default', ->
  # place code for your default task here
  return
