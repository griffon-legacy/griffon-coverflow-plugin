/*
 * Copyright 2009-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the 'License');
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an 'AS IS' BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Andres Almiray
 */
class CoverflowGriffonPlugin {
    // the plugin version
    String version = '0.6'
    // the version or versions of Griffon the plugin is designed for
    String griffonVersion = '0.9.5 > *'
    // the other plugins this plugin depends on
    Map dependsOn = [swing: '0.9.5']
    // resources that are included in plugin packaging
    List pluginIncludes = []
    // the plugin license
    String license = 'Apache Software License 2.0'
    // Toolkit compatibility. No value means compatible with all
    // Valid values are: swing, javafx, swt, pivot, gtk
    List toolkits = ['swing']
    // Platform compatibility. No value means compatible with all
    // Valid values are:
    // linux, linux64, windows, windows64, macosx, macosx64, solaris
    List platforms = []
    // URL where documentation can be found
    String documentation = ''
    // URL where source can be found
    String source = 'https://github.com/griffon/griffon-coverflow-plugin'

    List authors = [
        [
            name: 'Andres Almiray',
            email: 'aalmiray@yahoo.com'
        ]
    ]
    String title = 'Animated Coverflow component'
    // accepts Markdown syntax. See http://daringfireball.net/projects/markdown/ for details
    String description = '''
This plugin provides a *coverflow* component useful for displaying an image set. The component is based on Romain Guy's
work explained at [http://www.curious-creature.org/2005/07/09/a-music-shelf-in-java2d][1]. In 2008 Kevin Long refactored
the code to be more generic and component-like. See [http://blog.codebeach.com/2008/02/imageflow-swing-component.html][2].
Finally the components received another face lift in terms of observable properties and the usage of a `ListModel` to hold the items.

Usage
-----

The following nodes will become available on a View script upon installing this plugin

| Node          | Property      | Type            | Default                        | Required | Bindable | Notes                                                                             |
| ------------- | ------------- | --------------- | ------------------------------ | -------- | -------- | --------------------------------------------------------------------------------- |
| stackLayout   |               |                 |                                |          |          | exposes BOTTOM and TOP as constraints                                             |
| gradientPanel | gradientStart | Color           | Color(110, 110, 110)           | no       | yes      |                                                                                   |
|               | gradientEnd   | Color           | Color(0, 0, 0)                 | no       | yes      |                                                                                   |
| imageFlow     | items         | ImageFlowItem[] |                                | no       | no       | alternate values may be of type `List<ImageFlowItem>`, `ListModel` or `ImageFlow` |
|               | amount        | int             | 5                              | no       | yes      |                                                                                   |
|               | sigma         | double          |                                | no       | yes      |                                                                                   |
|               | itemFont      | Font            | Font("Dialog", Font.PLAIN, 24) | no       | yes      |                                                                                   |
|               | itemTextColor | Color           | Color.WHITE                    | no       | yes      |                                                                                   |
|               | itemSpacing   | double          | 0.4                            | no       | yes      |                                                                                   |
| imageFlowItem | file          | File            |                                | no       | no       | value can be a String. Alternate to url:, image:, inputStream:, resource:         |
|               | url           | URL             |                                | no       | no       | value can be a String. Alternate to file:, image:, inputStream:, resource:        |
|               | image         | Image           |                                | no       | no       | value can be a String. Alternate to file:, url:, inputStream:, resource:          |
|               | inputStream   | InputStream     |                                | no       | no       | Alternate to file:, url:, image:, resource:                                       |
|               | resource      | String          |                                | no       | no       | Alternate to file:, url:, image:, inputStream:                                    |

`imageFlowItem` can be nested inside `imageFlow` if and only if the model is mutable.

`ImageFlow` exposes the following methods to help you navigate and keep track of the current selection:

 *  **previous()** - navigates one step backward.
 *  **next()** - navigates one step forward.
 *  **getMinSelectionIndex()** - returns the smallest selected cell index.
 *  **getMaxSelectionIndex()** - returns the largest selected cell index.
 *  **getSelectedIndex()** - returns the first selected index; returns -1 if there is no selected item.
 *  **getSelectedValue()** - returns the first selected value, or null if the selection is empty.
 *  **isSelectedIndex(int)** - returns true if the specified index is selected.
 *  **setSelectedIndex(int)** - selects a single cell.
 *  **addListSelectionListener(ListSelectionListener)** - adds a listener to the list that's notified each time a change to the selection occur.
 *  **removeListSelectionListener(ListSelectionListener)** - removes a listener to the list that's notified each time a change to the selection occur.

The following view script shows a basic usage. Not that `imageFlow` allows nesting of `imageFlowItem` given that its defualt model is mutable.

        application(title: 'Coverflow',
          size: [520,320],
          locationByPlatform: true,
          iconImage: imageIcon('/griffon-icon-48x48.png').image,
          iconImages: [imageIcon('/griffon-icon-48x48.png').image,
                       imageIcon('/griffon-icon-32x32.png').image,
                       imageIcon('/griffon-icon-16x16.png').image]) {
          borderLayout()
          panel(constraints: CENTER) {
            stackLayout()
            gradientPanel(constraints: BOTTOM)
            imageFlow(id: "flow", constraints: TOP) {
              (1..10). each { i ->
                imageFlowItem(resource: "/griffon-icon-128x128.png", label: "Icon $i")
              }
            }
          }
          panel(constraints: WEST) {
            borderLayout()
            button("<",  actionPerformed: { flow.previous() })
          }
          panel(constraints: EAST) {
            borderLayout()
            button(">",  actionPerformed: { flow.next() })
          }
        }

[1]: http://www.curious-creature.org/2005/07/09/a-music-shelf-in-java2d/
[2]: http://blog.codebeach.com/2008/02/imageflow-swing-component.html
'''
}
