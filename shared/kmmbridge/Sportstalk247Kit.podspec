Pod::Spec.new do |spec|
    spec.name                     = 'Sportstalk247Kit'
    spec.version                  = '2.0.0-beta01'
    spec.homepage                 = 'https://gitlab.com/sportstalk247/sdk-kmm'
    spec.source                   = { :http=> ''}
    spec.authors                  = ''
    spec.license                  = ''
    spec.summary                  = 'Sportstalk247 SDK - KMM'
    spec.vendored_frameworks      = 'build/cocoapods/framework/Sportstalk247Kit.framework'
    spec.libraries                = 'c++'
    spec.ios.deployment_target = '15.4'
                
                
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':shared:kmmbridge',
        'PRODUCT_MODULE_NAME' => 'Sportstalk247Kit',
    }
                
    spec.script_phases = [
        {
            :name => 'Build Sportstalk247Kit',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED" ]; then
                  echo "Skipping Gradle build task invocation due to OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED environment variable set to \"YES\""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/../../gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration="$CONFIGURATION"
            SCRIPT
        }
    ]
                
end