# WolfRider

A Paper plugin that lets players ride their tamed wolves.

*Some say this plugin fulfills an ancient dream — whispered through chat logs and murmured in the cold glow of server terminals. Haksnbot knows.*

## Features

- **Mount tamed wolves** - Shift+right-click your tamed wolf to hop on
- **Bone steering** - Hold a bone in your main hand to control movement
- **Jump capability** - Look upward while riding to make your wolf leap
- **Owner protection** - Only the wolf's owner can ride it (configurable)
- **Configurable speed** - Adjust how fast wolves run

## Requirements

- Paper 1.21.4 or newer
- Java 21+

## Installation

1. Download `WolfRider-1.0.0.jar` from [Releases](https://github.com/haksndot/wolf-rider/releases)
2. Place the JAR in your server's `plugins/` folder
3. Restart your server

## Usage

1. Tame a wolf with bones (vanilla mechanic)
2. Shift+right-click your tamed wolf to mount it
3. Hold a bone in your main hand to steer
4. Look in the direction you want to go
5. Look upward (pitch < -30°) to jump
6. Dismount with Shift (sneak)

## Configuration

After first run, edit `plugins/WolfRider/config.yml`:

```yaml
# Movement speed multiplier (1.0 = normal speed)
ride-speed: 1.0

# Require holding a bone in main hand to steer
require-bone: true

# Only allow the wolf's owner to ride it
owner-only: true
```

### Options

| Option | Default | Description |
|--------|---------|-------------|
| `ride-speed` | `1.0` | Speed multiplier. Higher = faster. Base speed is ~0.3 blocks/tick. |
| `require-bone` | `true` | If `true`, player must hold a bone to control the wolf. |
| `owner-only` | `true` | If `true`, only the wolf's owner can mount it. |

## Building from Source

```bash
./gradlew build
```

The compiled JAR will be at `build/libs/WolfRider-1.0.0.jar`.

## License

MIT
