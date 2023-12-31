/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2023 Cloud Software Group, Inc. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */

function renderResult(returnSVG) {
	return new Promise(function(resolve, reject) {
		var interval;
		interval = window.setInterval(function() {
			var rendered = cvComponentRendered();
			if (rendered) {
				try {
					clearInterval(interval);
					var result = returnSVG ? cvComponentSVG() : cvComponentDimensions();
					resolve(result);
				} catch (err) {
					reject(err);
				}
			}
		}, 50);
	});
}

function cvComponentRendered() {
    if (typeof window.componentRendered !== 'undefined') {
        if (window.componentRendered === true) {
            return true;
        }
        return false;
    }
    return document.getElementsByTagName("svg").length > 0;
}

function cvComponentSVG() {
    function getStyles(doc) {
        var styles = "",
            styleSheets = doc.styleSheets;

        if (styleSheets) {
            for (var i = 0; i < styleSheets.length; i++) {
                processStyleSheet(styleSheets[i]);
            }
        }

        function processStyleSheet(ss) {
            if (ss.cssRules && ss.cssRules !== null) {
                for (var i = 0; i < ss.cssRules.length; i++) {
                    var rule = ss.cssRules[i];
                    if (rule.type === 3) {
                        // Import Rule
                        processStyleSheet(rule.styleSheet);
                    } else {
                        // hack for illustrator crashing on descendent selectors
                        if (rule.selectorText) {
                            if (rule.selectorText.indexOf(">") === -1) {
                                styles += "\n" + rule.cssText;
                            }
                        }
                    }
                }
            }
        }

        return styles;
    }

    function getSources(doc, styles) {
        var doctype = '<?xml version="1.0" encoding="UTF-8" standalone="no"?><!DOCTYPE svg PUBLIC "-//W3C//DTD SVG 1.1//EN" "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd">';
        var svgInfo = [],
            svgs = doc.getElementsByTagName("svg");

        for (var i = 0; i < svgs.length; i++) {
            var svg = svgs[i];
            svg.setAttribute("version", "1.1");

            // removing attributes so they aren't doubled up
            svg.removeAttribute("xmlns");

            var defsTags = svg.getElementsByTagName("defs"),
                defs_element,
                styleElem;

            if (defsTags.length == 0) {
                defs_element = document.createElement('defs');

                if (defs_element.hasAttribute("xmlns")) {
                    defs_element.removeAttribute("xmlns");
                }

                svg.insertBefore(defs_element, svg.firstChild);
            } else {
                defs_element = defsTags[0];
            }


            if (typeof window.cvcIgnoreSVGStyles !== 'undefined' && window.cvcIgnoreSVGStyles == true) {
              // Don't add any style, since the batik may not like it.
            } else if (styles) {
                styleElem = document.createElement('style');
                styleElem.type = 'text/css';
                styleElem.appendChild(document.createTextNode(styles));
                defs_element.appendChild(styleElem);
            }

            var source = (new XMLSerializer()).serializeToString(svg).replace('<defs xmlns="http://www.w3.org/1999/xhtml">','<defs>'); //.replace('</style>', '<![CDATA[' + styles + ']]></style>');
            var rect = svg.getBoundingClientRect();

            svgInfo.push({
                top: rect.top,
                left: rect.left,
                width: rect.width,
                height: rect.height,
                class: svg.getAttribute("class"),
                id: svg.getAttribute("id"),
                childElementCount: svg.childElementCount,
                source: doctype + source
            });
        }

        return svgInfo;
    }

    var styles = getStyles(document);
    var sources = getSources(document, styles);

    // This should never be true...
    if (sources.length == 0) {
        throw "No SVG found in this html.";
    }

    return sources[0].source;
}

function cvComponentDimensions() {
    var body = document.getElementsByTagName('body')[0];

    body.style.marginTop = '0px';
    body.style.marginLeft = '0px';
    var element = body.children[0];

    return {
        w: Number(element.offsetWidth),
        h: Number(element.offsetHeight)
    };
}
