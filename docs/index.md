formula
=======

![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/vinctustech/formula?include_prereleases) ![GitHub (Pre-)Release Date](https://img.shields.io/github/release-date-pre/vinctustech/formula) ![GitHub last commit](https://img.shields.io/github/last-commit/vinctustech/formula) ![GitHub](https://img.shields.io/github/license/vinctustech/formula)

*formula* is a parser and evaluator for math functions and formula's. It lets you declare variables which function as mutable inputs for expressions that can be re-computed as the inputs are changed.

Documentation
-------------

See https://vinctustech.github.io/formula/.

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

```typescript
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
