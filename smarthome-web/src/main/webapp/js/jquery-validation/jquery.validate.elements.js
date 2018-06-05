/**
 * 由于jquery.valdate插件是根据input的name来判断元素唯一性的, 因此如果页面中有多个name相同的元素, 验证时只会对一个起效!
 * 
 * @see http://stackoverflow.com/questions/931687/using-jquery-validate-plugin-to-validate-multiple-form-fields-with-identical-nam
 */
$(function () {
if ($.validator) {
    //fix: when several input elements shares the same name, but has different id-ies....
    $.validator.prototype.elements = function () {

        var validator = this,
            rulesCache = {};

        // select all valid inputs inside the form (no submit or reset buttons)
        // workaround $Query([]).add until http://dev.jquery.com/ticket/2114 is solved
        return $([]).add(this.currentForm.elements)
        .filter(":input")
        .not(":submit, :reset, :image, [disabled]")
        .not(this.settings.ignore)
        .filter(function () {
            var elementIdentification = this.id || this.name;

            !elementIdentification && validator.settings.debug && window.console && console.error("%o has no id nor name assigned", this);

            // select only the first element for each name, and only those with rules specified
            if (elementIdentification in rulesCache || !validator.objectLength($(this).rules()))
                return false;

            rulesCache[elementIdentification] = true;
            return true;
        });
    };
}
});