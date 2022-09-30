formula
=======

![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/vinctustech/formula?include_prereleases) ![GitHub (Pre-)Release Date](https://img.shields.io/github/release-date-pre/vinctustech/formula) ![GitHub last commit](https://img.shields.io/github/last-commit/vinctustech/formula) ![GitHub](https://img.shields.io/github/license/vinctustech/formula)

*formula* is a parser and evaluator for math functions and formulae. It lets you declare variables which function as mutable inputs for expressions that can be re-computed as the inputs are changed.

Documentation
-------------

See [documentation](https://vinctustech.github.io/formula/).

Usage
-----

### TypeScript

Install by typing

```shell
npm install @vinctus/formula
```

The typings are built-in. Use the following `import` in your code:

```
import { Formulae } from '@vinctus/formula'
```

### Scala

Include the following in your `project/plugins.sbt`:

```sbt
addSbtPlugin("com.codecommit" % "sbt-github-packages" % "0.5.3")
```

Include the following in your `build.sbt`:

```sbt
resolvers += Resolver.githubPackages("vinctustech")

libraryDependencies += "io.github.vinctustech" %%% "formula" % "0.0.33"
```

Use the following `import` in your code:

```scala
import io.github.vinctustech.formula.Formulae
```

The obligatory "Hello World" example
------------------------------------

Although *formula* is mainly for doing math, it can handle strings and also boolean values in a limited way. For example, you might want a certain text to vary depending on some condition. The following is a vanilla React 18 example.

```tsx
import React, { FC, useState } from 'react'
import { createRoot } from 'react-dom/client'
import { Formulae } from '@vinctus/formula'

const f = new Formulae(`
    const positive = 'Hello World!'
    const negative = 'Goodbye World!'
    const min = 0
    const max = 100
    const initial = min
    
    var input = initial
    
    def radians = (input - min)/max*pi
    def output = cos(radians)
    def message = output >= 0 ? positive : negative
    `)

export const App: FC = () => {
  const [value, setValue] = useState<number>(f.get('initial'))

  f.set('input', () => value)

  return (
    <div style={{ marginTop: '20px', marginLeft: '20px' }}>
      <h3>{f.formula('message')}</h3>
      <input
        type="range"
        min={f.get('min')}
        max={f.get('max')}
        value={value}
        onChange={(e) => {
          setValue(Number(e.target.value))
        }}
      />
      <p>
        <code>
          cos({f.get('radians')}) = {f.formula('output')}
        </code>
      </p>
    </div>
  )
}

createRoot(document.getElementById('app')!).render(<App />)
```

License
-------

[ISC](https://github.com/vinctustech/formula/blob/main/LICENSE)

Syntax
------

Built-in Functions
------------------

<dl>
    <dt>abs(x)</dt><dd>absolute value of <i>x</i></dd>
    <dt>acos(x)</dt><dd>inverse cosine (in radians) of <i>x</i></dd>
    <dt>asin(x)</dt><dd>inverse sin (in radians) of <i>x</i></dd>
    <dt>atan(x)</dt><dd>inverse tangent (in radians) of <i>x</i></dd>
    <dt>atan2(x,y)</dt><dd>counterclockwise angle (in radians) between the positive x-axis and the point (<i>x</i>, <i>y</i>)</dd>
    <dt>ceil(x)</dt><dd>smallest integer greater than or equal to <i>x</i></dd>
    <dt>cos(x)</dt><dd>cosine of <i>x</i></dd>
    <dt>exp(x)</dt><dd><i>e</i> raised to the power of <i>x</i></dd>
    <dt>floor(x)</dt><dd>largest integer less than or equal to <i>x</i></dd>
    <dt>log(x)</dt><dd>base 10 logarithm of <i>x</i></dd>
    <dt>ln(x)</dt><dd>natural logarithm of <i>x</i></dd>
    <dt>pow(x,y)</dt><dd><i>x</i> raised to the power of <i>y</i></dd>
    <dt>round(x)</dt><dd>nearest integer to <i>x</i></dd>
    <dt>sign(x)</dt><dd>sign of <i>x</i></dd>
    <dt>sin(x)</dt><dd>sine of <i>x</i></dd>
    <dt>sqrt(x)</dt><dd>square root of <i>x</i></dd>
    <dt>tan(x)</dt><dd>tangent of <i>x</i></dd>
</dl>

Built-in Constants
------------------

<dl>
    <dt>e</dt><dd>&#8455; is the base of the natural logarithm</dd>
    <dt>pi</dt><dd>π is the ratio of a circle's circumference to its diameter</dd>
</dl>
